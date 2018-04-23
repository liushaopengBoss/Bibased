package cn.edu.zzti.bibased.service.operation.boss;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.execute.BaseExecuter;
import cn.edu.zzti.bibased.execute.PositionDetailExecute;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.BossHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.thread.AnsyTask;
import cn.edu.zzti.bibased.utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

@Service
public class BossGetService {
    private Logger logger = LoggerFactory.getLogger(BossGetService.class);
    @Resource
    private ThreadPoolTaskExecutor getInfoPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService completionService;
    @Resource
    private BossQueryService bossQueryService;
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private EmailService emailService;
    @Resource
    private AcquisitionService acquisitionService;

    public void getBossPositionType(){
        String apiUrl = "https://www.zhipin.com/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.bossGetHeader);
        List<Positions> jobs = BossHandler.getJobs(html);
        acquisitionService.batchAddJob(jobs);
    }


    public void getBossPositionTypeV2(){
        List<Positions> positionType = this.getPositionType();
        acquisitionService.batchAddJob(positionType);
    }

    private List<Positions> getPositionType(){
        String apiUrl = "https://www.zhipin.com/common/data/position.json";
        String  sourceJson = httpClientService.doGet(apiUrl,null,HttpHeaderConstant.bossGetHeader);
        return BossHandler.getJobsV2(sourceJson);
    }

    public List<City> getHotCity(){
        String apiUrl = "https://www.zhipin.com/common/data/city.json";
        String  sourceJson = httpClientService.doGet(apiUrl,null,HttpHeaderConstant.bossGetHeader);
        return  BossHandler.getHotCity(sourceJson);
    }
    public void getCity(){
        String apiUrl = "https://www.zhipin.com/common/data/city.json";
        String  sourceJson = httpClientService.doGet(apiUrl,null,HttpHeaderConstant.bossGetHeader);
        List<City> city = BossHandler.getCity(sourceJson);
        acquisitionService.batchAddCity(city);
    }

   public void getPositionDetails(){
        //get hot city
       Future<List<City>> cityFuture = getInfoPool.submit(() -> {
           return this.getHotCity();
       });
       Future<List<Positions>> positionFuture = getInfoPool.submit(() -> {
           return bossQueryService.queryLeafPositions();
       });
       List<City> citys = new ArrayList<>();
       List<Positions> positions = new ArrayList<>();
      try{
           citys =  cityFuture.get();
          positions = positionFuture.get();
      }catch (Exception e){
        logger.error("get city or position faild ! !",e);
      }

       if (!CollectionUtils.isEmpty(citys) && !CollectionUtils.isEmpty(positions)) {
          logger.info("数据采集开始！！");
           for (Positions position : positions) {
               for (City city : citys) {

                   if("全国".equals(city.getCityName())){
                       continue;
                   }
                   logger.info("正在采集城市："+city.getCityName()+" 职位:" +position.getPositionName());
                   String apiUrl = getAppendUrl(city, position);
                   for (int i = 1; i < 2; i++) {
                       BaseExecuter positonDetailTask = (PositionDetailExecute) SpringContextUtils.getBean(PositionDetailExecute.class);
                       if (i == 1) {
                           positonDetailTask.setParams(null);
                       } else {
                           Map<String, Object> param = new HashMap<>();
                           param.put("ka", "sel-city-" + city.getCode());
                           positonDetailTask.setParams(param);
                       }
                       Map<String, Object> bossPositionDetailGetHeader = HttpHeaderConstant.bossPositionDetailGetHeader;
                       getHeader(city, position, bossPositionDetailGetHeader, i);
                       positonDetailTask.setHeaders(bossPositionDetailGetHeader);
                       positonDetailTask.setApiUrl(apiUrl);
                       positonDetailTask.setWebsiteEnum(WebsiteEnum.BOSS);
                       completionService.submit(AnsyTask.newTask().registExecuter(positonDetailTask));
                   }
                   List<PositionDetail> positionDetails = new ArrayList<>();
                   for (int i = 1; i < 2; i++) {
                       try {
                           Future<List<PositionDetail>> take = completionService.take();
                           if (take.isCancelled()) {
                               continue;
                           }
                           List<PositionDetail> positionList = take.get();
                           positionDetails.addAll(positionList);
                       } catch (Exception e) {
                           logger.error("boss get positionDetail future faild !!", e);
                       }
                   }

                   if (!CollectionUtils.isEmpty(positionDetails)) {
                       handlePositionDetail(positionDetails,position.getPositionName());
                       logger.info("数据写入数据库！！"+city.getCityName()+" size:" +positionDetails.size());
                       acquisitionService.batchAddPositionDetails(positionDetails);
                   }
               }
           }

           logger.info("数据采集结束！！");
       }
   }

  private void handlePositionDetail(List<PositionDetail> positionList,String positionName){
       if(!CollectionUtils.isEmpty(positionList)){
           positionList.forEach(p->{
               if(p.getPositionName().contains("实习")){
                   p.setJobNature("实习");
               }else{
                   p.setJobNature("全职");
               }
               p.setThirdType(positionName);
           });
       }
  }
  private String getAppendUrl(City city,Positions positions){
      StringBuffer buffer = new StringBuffer("https://www.zhipin.com/");
      //https://www.zhipin.com/c101280600-p100103/?ka=sel-city-101280600
      buffer.append("c").append(city.getCode()).append("-p").append(positions.getPositionUrl()).append("/");
        return buffer.toString();
  }
  private void getHeader(City city, Positions position,Map<String, Object> header,int pageNo){
      StringBuffer buffer = new StringBuffer("/");
      buffer.append("c").append(city.getCode()).append("-p").append(position.getPositionUrl()).append("/");
      header.put("referer","https://www.zhipin.com"+buffer.toString());
      if(pageNo!=1){
          buffer.append("?page="+pageNo+"&ka=page-"+pageNo);
      }
      header.put(":path",buffer.toString());

      header.put("cookie","lastCity="+city.getCode()+"; JSESSIONID=\"\";" +
              " Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1521947993,1522511497,1522570866,1522849305;" +
              " __c=1522849305; __g=-; __l=l=%2Fwww.zhipin.com%2F&r=; " +
              "toUrl=https%3A%2F%2Fwww.zhipin.com%2Fjob_detail%2F; " +
              "t=2hC6RJArNhx9pdCs; wt=2hC6RJArNhx9pdCs; " +
              "Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1523014640;" +
              " __a=60528412.1515908295.1522570866.1522849305.89.5.53.89");
  }
}
