package cn.edu.zzti.bibased.service.operation.lagou;

import cn.edu.zzti.bibased.aspect.ActionLog;
import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.dto.lagou.*;
import cn.edu.zzti.bibased.execute.BaseExecuter;
import cn.edu.zzti.bibased.execute.CompanyExecute;
import cn.edu.zzti.bibased.execute.PositionDetailExecute;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.thread.*;
import cn.edu.zzti.bibased.utils.DateUtils;
import cn.edu.zzti.bibased.utils.SpringContextUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

/**
 * 采集拉勾网数据
 */
@Service
public class LagouGetService {
    private static final Logger logger = LoggerFactory.getLogger(LagouGetService.class);
    @Resource
    private ThreadPoolTaskExecutor getInfoPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService completionService;
    @Resource
    private LagouQueryService lagouQueryService;
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private EmailService emailService;
    @Resource
    private AcquisitionService acquisitionService;

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

    /**
     * 多线程执行数据
     */
    public void initLagouInfo(){
        getInfoPool.execute(()-> {
                getJobInformation();
        });
        getInfoPool.execute(()->{
            getCityInformation();
        });

    }
    /**
     * 采集拉勾网的职位分类信息
     *
     */
    @Async
    @ActionLog(ProjectItem.POSITION)
    public void getJobInformation(){
        String url = "https://www.lagou.com";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<Positions> jobs = LagouHandler.getJobs(html);
        acquisitionService.batchAddJob(jobs);
    }
    /**
     * 采集拉勾网的城市信息
     *
     */

    @ActionLog(ProjectItem.CITY)
    @Async
    public void getCityInformation(){
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<City> jobs = LagouHandler.getCitys(html);
        acquisitionService.batchAddCity(jobs);
    }
    /**
     * 采集拉勾网的公司信息
     */
//    public void getCompanyInfomation(){
//        String apiUrl = "https://www.lagou.com/gongsi/";
//        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.lagouGetHeader);
//        List<City> cityByCompany = LagouHandler.getCityByCompany(html);
//        String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
//        //去掉第一个和最后一个
//        for (int i = 1; i < cityByCompany.size()-1; i++) {
//            Gson gson = new Gson();
//            String url = apiUrl+cityByCompany.get(i).getLinkId()+"-0-0.json";
//            logger.info(url);
//            Map<String,Object> param = new LinkedHashMap<>();
//            param.put("first",false);
//            param.put("pn",1);
//            param.put("sortField",0);
//            param.put("havemark",0);
//            String data = httpClientService.doPost(url, param, HttpHeaderConstant.lagouAjaxHeader);
//            CompanyResultJsonVO companyResultJsonVO = gson.fromJson(data, CompanyResultJsonVO.class);
//            int pageNo = companyResultJsonVO.getTotalCount()/companyResultJsonVO.getPageSize();
//            logger.info("-----------page:"+pageNo+"\n");
//            LaGouTask laGouTask = new LaGouTask(url,param, HttpType.POST);
//            List<CompanyVO> resultVOS = new LinkedList<>();
//            resultVOS.addAll(companyResultJsonVO.getResult());
//            for (int j = 2; j <= pageNo; j++) {
//                for (int k = 0; k < 10; k++) {
//                    param.put("first",false);
//                    param.put("pn",j);
//                    completionService.submit(laGouTask);
//                    j++;
//                }
//                logger.info("-------------------------->"+j+"\n");
//                for (int k = 0; k < 10; k++) {
//                    try{
//                        Future<String> take = completionService.take();
//                        if(take.get()!=null){
//                            CompanyResultJsonVO companyResultJsonVO1 = gson.fromJson(take.get(), CompanyResultJsonVO.class);
//                            List<CompanyVO> result = companyResultJsonVO1.getResult();
//                            resultVOS.addAll(result);
//                        }
//                    }catch (Exception e){
//                        logger.error(e.getMessage(),e);
//                    }
//                }
//
//                if (resultVOS.size() > 0) {
//                    handleCompany(resultVOS);
//                }else{
//                    break;
//                }
//            }
//            try {
//                Thread.sleep(3000);
//            }catch (Exception e){}
//        }
//    }

    /**
     * 获取拉钩的公司信息 V2版本
     */
    @Async
    @ActionLog(ProjectItem.COMPANY)
    public void getCompanyInfomationV2(){
        String apiUrl = "https://www.lagou.com/gongsi/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.lagouGetHeader);
        List<City> cityByCompany = LagouHandler.getCityByCompany(html);
        //去掉第一个和最后一个
        for (int i = 1; i < cityByCompany.size()-1; i++) {
            Gson gson = new Gson();
            String url = apiUrl+cityByCompany.get(i).getLinkId()+"-0-0.json";
            logger.info("num:"+i+" "+cityByCompany.get(i).getCityName()+"  "+url);
            Map<String, Object> lagouAjaxHeader = HttpHeaderConstant.lagouAjaxHeader;
            setCookie(lagouAjaxHeader);
            lagouAjaxHeader.put("Referer",url.replace(".json",""));
            String data = httpClientService.doPost(url, HttpHeaderConstant.compaanyParam, lagouAjaxHeader);
            logger.debug(data);
            if(data == null){
                logger.error("-----------获取数据出现异常------出现null-------");
                try {
                    Thread.sleep(60000);
                    cityByCompany.add(cityByCompany.get(i));
                    continue;
                }catch (Exception e){}
            }
            CompanyResultJsonVO companyResultJsonVO = gson.fromJson(data!=null?data:"{}", CompanyResultJsonVO.class);
            int totalPageNo = companyResultJsonVO.getTotalCount()/companyResultJsonVO.getPageSize();
            logger.info("-----------page:"+totalPageNo+"\n");
            BaseExecuter companyTask = (CompanyExecute)SpringContextUtils.getBean(CompanyExecute.class);
            companyTask.setApiUrl(url);
            companyTask.setHeaders(lagouAjaxHeader);
            companyTask.setParams(HttpHeaderConstant.compaanyParam);
            List<CompanyVO> resultVOS = new LinkedList<>();
            resultVOS.addAll(companyResultJsonVO.getResult());
            for (int j = 2; j <= 20; j++) {
                for (int k = 0; k < 10; k++) {
                    Map<String, Object> companyParam = HttpHeaderConstant.compaanyParam;
                    companyParam.put("first",false);
                    companyParam.put("pn",j);
                    lagouAjaxHeader.put("Referer",url.replace(".json",""));
                    setCookie(lagouAjaxHeader);
                    completionService.submit(AnsyTask.newTask().registExecuter(companyTask));
                    j++;
                }
                logger.info("-------------------------->"+j+"\n");
                for (int k = 0; k < 10; k++) {
                    try{
                        Future<CompanyResultJsonVO> take = completionService.take();
                        if(take.get()!=null){
                            CompanyResultJsonVO companyResultJsonVO1 = take.get();
                            List<CompanyVO> result = companyResultJsonVO1.getResult();
                            resultVOS.addAll(result);
                        }
                    }catch (Exception e){
                        logger.error(e.getMessage(),e);
                    }
                }

                if (resultVOS.size() > 0) {
                    j--;
                    handleCompany(resultVOS);
                }else{
                    break;
                }
            }

        }
    }

    /**
     * 拉钩城市信息 并且批量写入数据库
     * @param companyVOS
     */
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
            acquisitionService.batchAddCompany(targetCompany);
        }
    }

    /**
     * 获取拉钩网的职位详情信息
     *
     */
    @Async
    @ActionLog(ProjectItem.POSITIONDETAIL)
    public void getPositionDeailsInfomation(){
        emailService.sendSimpleMail("信息开始采集！","时间"+DateUtils.formatStr(new Date(),DateUtils.YYMMDD_HHmmSS));
        Future<List<Positions>> positionListFuter = getInfoPool.submit(() -> {
               return acquisitionService.queryLeftPositions();
        });

        List<Positions> positions = null;
        List<City> citys =acquisitionService.queryCitys(WebsiteEnum.LAGOU.getWebCode());
        try{
            positions = positionListFuter.get();
        }catch (Exception e){}
        if(!CollectionUtils.isEmpty(positions) && !CollectionUtils.isEmpty(citys)){
            positions.forEach(position -> {
                String positionName = position.getPositionName();
                Map<String, Object> param = new LinkedHashMap<>();
                param.put("first", true);
                param.put("pn", 1);
                param.put("kd", positionName);
                Map<String, Object> lagouAjaxHeader = HttpHeaderConstant.lagouAjaxHeader;
                for (City city : citys) {
                    if (city.getCityName().contains("城市")||city.getCityName().contains("全部")) {
                        continue;
                    }
                    String apiUrl = "https://www.lagou.com/jobs/positionAjax.json?px=default&city=" + city.getCityName() + "&needAddtionalResult=false&isSchoolJob=0";
                    logger.info("---->  " + apiUrl);
                    logger.info("---->  https://www.lagou.com/jobs/list_" + positionName.trim() + "?px=default&city=" + city.getCityName());
                    lagouAjaxHeader.put("Referer", "https://www.lagou.com/jobs/list_" + positionName.trim() + "?px=default&city=" + city.getCityName());
                    setCookie(lagouAjaxHeader);
                    BaseExecuter executer = (PositionDetailExecute) SpringContextUtils.getBean(PositionDetailExecute.class);
                    executer.setApiUrl(apiUrl);
                    executer.setHeaders(lagouAjaxHeader);
                    executer.setParams(param);
                    int pageSize = executer.getPageSize();
                    pageSize = pageSize > 20 ? 20 : pageSize;
                    isSleep(pageSize);
                    Long lastCreateTime = lagouQueryService.queryLastPositionCreateTime(city.getCityName(), positionName);
                    if (pageSize != 0 || pageSize != 1) {
                        logger.info("爬虫开始获取数据：城市："+city.getCityName()+"  职位："+positionName);
                        for (int i = 1; i <= pageSize; i++) {
                            int pageNum = pageSize < 10  ? pageSize : 10;
                            for (int j = 0; j < pageNum; j++) {
                                Map<String, Object> param2 = new LinkedHashMap<>();
                                param2.put("first", false);
                                param2.put("pn", i);
                                param2.put("kd", positionName);
                                logger.debug("页数为---->  " + i);
                                setCookie(lagouAjaxHeader);
                                BaseExecuter positonDetailTask = (PositionDetailExecute) SpringContextUtils.getBean(PositionDetailExecute.class);
                                positonDetailTask.setParams(param2);
                                positonDetailTask.setHeaders(lagouAjaxHeader);
                                positonDetailTask.setApiUrl(apiUrl);
                                positonDetailTask.setWebsiteEnum(WebsiteEnum.LAGOU);
                                completionService.submit(AnsyTask.newTask().registExecuter(positonDetailTask));
                                i++;
                            }
                            boolean isBreak = false;
                            List<PositionDetail> positionDetails = new LinkedList<>();
                            int count = 0;
                            for (int k = 0; k < pageNum; k++) {
                                try {
                                    Future<PositionDetailResultJsonVo> take = completionService.take();
                                    if (take.get() != null) {
                                        PositionDetailResultJsonVo positionDetailResultJsonVo = take.get();
                                        List<PositionDetailVo> result = positionDetailResultJsonVo.getResult();
                                        if (result == null || (result != null && result.size() == 0) ) {
                                            isBreak = true;
                                        }
                                        List<PositionDetail> positionDetailsList = handlePositionDetails(result, lastCreateTime, positionName);
                                        if (result == null ||  (result!=null &&positionDetailsList.size() < result.size())) {
                                            count++;
                                        }
                                        positionDetails.addAll(positionDetailsList);

                                    } else {
                                        logger.error("future -->获取数据失败！");
                                    }
                                } catch (Exception e) {
                                    isBreak = true;
                                    logger.error("获取数据失败！", e);
                                    emailService.sendSimpleMail("信息告警","获取数据失败！"+e);
                                }
                            }

                            if (positionDetails.size() > 0) {
                                acquisitionService.batchAddPositionDetails(positionDetails);
                                i--;
                                logger.info("数据获取成功-->异步写入数据  ");
                            } else if (isBreak || positionDetails.size() == 0 || count == 2) {
                                break;
                            }

                        }

                    }
                    logger.info("职位："+positionName+"城市："+city.getCityName());
                }
                logger.info("职位："+positionName+"抓取完成");
            });
        }
        emailService.sendSimpleMail("信息采集结束！","时间"+DateUtils.formatStr(new Date(),DateUtils.YYMMDD_HHmmSS));
    }

    /**
     * 数据页为0 拉钩没有数据  所以选择睡10秒
     * @param pageSize
     */
    private void isSleep(int pageSize){
        if(pageSize ==0){
            try {
                Thread.sleep(10000);
            }catch (Exception e){}
        }
    }
    /**
     * 组装职位详情信息
     * @param positionDetailVos
     * @param lastCreateTime
     * @return
     */
    private List<PositionDetail> handlePositionDetails(List<PositionDetailVo> positionDetailVos,Long  lastCreateTime,String positionName){
        List<PositionDetail>   positionDetails = new LinkedList<>();
        if(!CollectionUtils.isEmpty(positionDetailVos)){
            for (int i = 0; i < positionDetailVos.size(); i++) {
                PositionDetailVo positionDetailVo = positionDetailVos.get(i);
                PositionDetail positionDetail = new PositionDetail();
                BeanUtils.copyProperties(positionDetailVo,positionDetail);
                String gps = new Gson().toJson(new GPS(positionDetailVo.getLongitude(), positionDetailVo.getLatitude()));
                positionDetail.setGps(gps);
                positionDetail.setThirdType(positionName);
                String[] workYears = positionDetailVo.getWorkYear().replace("年", "").replace("不限","").replace("以上","").split("-");
                if(workYears==null || workYears.length==0 || workYears.length ==1){
                    positionDetail.setWorkMaxYear(0);
                    positionDetail.setWorkMinYear(0);
                }else if(workYears.length ==1){
                    positionDetail.setWorkMaxYear(Integer.valueOf(workYears[0]));
                    positionDetail.setWorkMinYear(Integer.valueOf(workYears[0]));

                }else{
                    positionDetail.setWorkMaxYear(Integer.valueOf(workYears[1]));
                    positionDetail.setWorkMinYear(Integer.valueOf(workYears[0]));
                }
                String []salary = positionDetailVo.getSalary() != null? positionDetail.getSalary().toLowerCase().replace("k","").replace("以上","").split("-"): null;
                if(salary ==null || salary.length ==0){
                    positionDetail.setMinSalary(new BigDecimal(0));
                    positionDetail.setMaxSalary(new BigDecimal(0));
                }else if(salary.length ==1){
                    positionDetail.setMinSalary(new BigDecimal(Double.valueOf(salary[0])));
                    positionDetail.setMaxSalary(new BigDecimal(Double.valueOf(salary[0])));
                }else{
                    positionDetail.setMinSalary(new BigDecimal(Double.valueOf(salary[0])));
                    positionDetail.setMaxSalary(new BigDecimal(Double.valueOf(salary[1])));
                }
                String []companySize =   positionDetailVo.getCompanySize() !=null ? positionDetailVo.getCompanySize().replace("少于","").replace("以上","").replace("人","").split("-"):null;
                if(companySize ==null || companySize.length==0){
                    positionDetail.setCompanyMaxSize(0);
                    positionDetail.setCompanyMinSize(0);
                }else if(companySize.length ==1){
                    positionDetail.setCompanyMaxSize(Integer.valueOf(companySize[0]));
                    positionDetail.setCompanyMinSize(Integer.valueOf(companySize[0]));
                }else{
                    positionDetail.setCompanyMaxSize(Integer.valueOf(companySize[1]));
                    positionDetail.setCompanyMinSize(Integer.valueOf(companySize[0]));
                }

                positionDetail.setPositionCreateTime(DateUtils.parseInt(positionDetailVo.getCreateTime()));
                positionDetail.setCompanyLabelList(positionDetailVo.getCompanyLabelList() != null ?positionDetailVo.getCompanyLabelList().toString():null);
                positionDetail.setBusinessZones(positionDetailVo.getBusinessZones() !=null ? positionDetailVo.getBusinessZones().toString() : null);
                positionDetail.setPositionLables(positionDetailVo.getPositionLables() !=null ? positionDetailVo.getPositionLables().toString() : null);
                positionDetail.setIndustryField(positionDetailVo.getIndustryField() !=null ? positionDetailVo.getIndustryField().toString() : null);
                positionDetail.setInclude(WebsiteEnum.LAGOU.getWebCode());
                if(lastCreateTime < positionDetail.getPositionCreateTime()){
                    positionDetails.add(positionDetail);
                }
            }
        }
        return positionDetails;
    }

    private void setCookie(Map<String,Object> header){
        String cookie = header.get("Cookie").toString().substring(0,header.get("Cookie").toString().indexOf("SEARCH_ID=")+10).toString()+ UUID.randomUUID().toString().replace("-","").toString()+";";
        logger.debug(cookie);
        header.put("Cookie",cookie);
    }
}
