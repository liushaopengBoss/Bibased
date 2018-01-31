package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.constant.HttpType;
import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.dto.lagou.CompanyResultJsonVO;
import cn.edu.zzti.bibased.dto.lagou.CompanyVO;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.thread.LaGouTask;
import com.google.gson.Gson;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

@Service
public class LagouService {
    @Resource
    private ThreadPoolTaskExecutor lagouPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService<String> completionService;
    @Resource
    private LagouOperationService lagouOperationService;
    @Resource
    private HttpClientService httpClientService;
    public String startGetDate(String apiUrl, Map<String,Object> param,String httpType) throws Exception{
        LaGouTask laGouTask = new LaGouTask(apiUrl,param, httpType);
        for (int i = 0; i < 5; i++) {
            completionService.submit(laGouTask);
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            Future<String> take = completionService.take();
            if (take !=null) {
                buffer.append("第几次：" + i + "\n" + take.get()+"\n\n\n\n      \n");
            }
        }
        return buffer.toString();
    }

    public void initLagouInfo(){
        lagouPool.execute(new Runnable() {
            @Override
            public void run() {
                collectionJobInformation();
            }
        });
        lagouPool.execute(new Runnable() {
            @Override
            public void run() {
                collectionCityInformation();
            }
        });
    }
    /**
     * 采集拉勾网的职位信息
     *
     */
    private void collectionJobInformation(){
        String url = "https://www.lagou.com";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<Positions> jobs = LagouHandler.getJobs(html);
        lagouOperationService.batchAddJob(jobs);
    }
    /**
     * 采集拉勾网的城市信息
     *
     */
    private void collectionCityInformation(){
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<City> jobs = LagouHandler.getCitys(html);
        lagouOperationService.batchAddCity(jobs);
    }
    /**
     * 采集拉勾网的公司信息
     */
    public void collectionCompanyInfomation(){
        String apiUrl = "https://www.lagou.com/gongsi/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.lagouGetHeader);
        List<City> cityByCompany = LagouHandler.getCityByCompany(html);
        //去掉第一个和最后一个
        for (int i = 1; i < cityByCompany.size()-1; i++) {
            System.out.printf("chengshi:"+i);
            Gson gson = new Gson();
            apiUrl = apiUrl+cityByCompany.get(i).getLinkId()+"-0-0.json";
            Map<String,Object> param = new LinkedHashMap<>();
            param.put("first",true);
            param.put("pn",1);
            param.put("sortField",0);
            param.put("havemark",0);
            String data = httpClientService.doPost(apiUrl, param, HttpHeaderConstant.lagouAjaxHeader);
            CompanyResultJsonVO companyResultJsonVO = gson.fromJson(data, CompanyResultJsonVO.class);
            int pageNo = companyResultJsonVO.getTotalCount()/companyResultJsonVO.getPageSize();
            System.out.printf("-----------page:"+pageNo+"\n");
            LaGouTask laGouTask = new LaGouTask(apiUrl,param, HttpType.POST);
            List<CompanyVO> resultVOS = new LinkedList<>();
            resultVOS.addAll(companyResultJsonVO.getResult());
            List<Future> futureList = new ArrayList<>();
            for (int j = 2; j <= pageNo; j++) {
                for (int k = 0; k < 10; k++) {
                    param.put("first",false);
                    param.put("pn",j);
                    completionService.submit(laGouTask);
                    j++;
                }
                System.out.printf("-------------------------->"+j+"\n");
                for (int k = 0; k < 10; k++) {
                    try{
                        Future<String> take = completionService.take();
                        CompanyResultJsonVO companyResultJsonVO1 = gson.fromJson(take.get(), CompanyResultJsonVO.class);
                        List<CompanyVO> result = companyResultJsonVO1.getResult();
                        resultVOS.addAll(result);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                if (resultVOS.size() > 0) {
                    handleCompany(resultVOS);
                }else{
                    break;
                }
            }
            try {
                Thread.sleep(3000);
            }catch (Exception e){}
        }
    }
    private void clearThread(List<Future> futureList){
        if(futureList.size()>10){
            for(int i=0;i<futureList.size();i++){
                try {
                    Object o = futureList.get(i).get();
                    System.out.printf("o"+o);
                }catch (Exception e){}
            }
        }
    }
    void handleCompany(List<CompanyVO> companyVOS){
        if(!CollectionUtils.isEmpty(companyVOS)){
            List<Company> targetCompany = new LinkedList<>();
            for (CompanyVO vo:companyVOS) {
                Company company = new Company();
                targetCompany.add(company);
                BeanUtils.copyProperties(vo,company);
                company.setCompanyName(vo.getCompanyShortName());
                company.setCompanyUrl("https://www.lagou.com/gongsi/"+vo.getCompanyId());
                company.setResumeRate(vo.getProcessRate());
                company.setInclude(WebsiteEnum.LAGOU.getWebCode());
                company.setPositionSlogan(vo.getCompanyFeatures());
            }
            companyVOS.clear();
            StopWatch clock = new StopWatch();
            clock.start(); //计时开始
            lagouOperationService.batchAddCompany(targetCompany);
            clock.stop();
            long time = clock.getTime();
            System.out.printf(""+time+"\n");
        }
    }
}
