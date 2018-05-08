package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.dto.boss.BossBaseVo;
import cn.edu.zzti.bibased.execute.PositionDetailExecute;
import cn.edu.zzti.bibased.utils.DateUtils;
import cn.edu.zzti.bibased.utils.IDUtils;
import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 处理BOSS直聘的网页信息
 * <p>
 * Created by huaidou on  2018/3/27
 */
public class BossHandler {
    private static  Logger logger = LoggerFactory.getLogger(BossHandler.class);
    /**
     * 从BOSS直聘获取所有的职位类别信息
     *
     * @param html
     * @return
     */
    public static List<Positions> getJobs(String html){
        if(StringUtils.isNotEmpty(html)){
            Document bossJobs = Jsoup.parse(html);
            String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
            List<Positions> jobs =new LinkedList<>();
            Elements jobMenu = bossJobs.getElementsByClass("job-menu");
            for (int i = 0; i <jobMenu.size(); i++) {
                Elements menuSubs = jobMenu.get(i).getElementsByClass("menu-sub");
                for (int j = 0; j <menuSubs.size() ; j++) {
                    Elements text = menuSubs.get(j).getElementsByClass("text");
                    Elements h4 = menuSubs.get(j).getElementsByTag("h4");
                    for (int k = 0; k <text.size() ; k++) {
                        String rootName = h4.get(k).text();
                        long rootId = IDUtils.getJobsIntId();
                        Positions rootPositon = new Positions();
                        rootPositon.setInclude(WebsiteEnum.BOSS.getWebCode());
                        rootPositon.setPositionName(rootName);
                        rootPositon.setLeaf(Boolean.FALSE);
                        rootPositon.setDateVersion(dateVersion);
                        rootPositon.setParentId(0);
                        rootPositon.setRootId(rootId);
                        jobs.add(rootPositon);
                        Elements a = text.get(k).getElementsByTag("a");
                        for (int l = 0; l <a.size() ; l++) {
                            Positions leafPositon = new Positions();
                            leafPositon.setInclude(WebsiteEnum.BOSS.getWebCode());
                            leafPositon.setPositionName(a.get(l).text());
                            leafPositon.setDateVersion(dateVersion);
                            leafPositon.setParentId(rootId);
                            leafPositon.setRootId(rootId);
                            leafPositon.setPositionUrl("https://www.zhipin.com/"+a.get(l).attr("href"));
                            jobs.add(leafPositon);
                        }

                    }
                }
            }
            return jobs;
        }
        return new LinkedList<>();
    }
    public static List<Positions> getJobsV2(String sourceJson){
        List<Positions> positions = new ArrayList<>();
        if(StringUtils.isNotEmpty(sourceJson)){
            JsonElement jsonElement = new JsonParser().parse(sourceJson);
            String targetJson = jsonElement.getAsJsonObject().get("data").toString();
            if(StringUtils.isNotEmpty(targetJson)){
                List<BossBaseVo> bossBaseVos = JSON.parseArray(targetJson, BossBaseVo.class);
                String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
                handlePositions(bossBaseVos,positions,dateVersion,0L,0L);
            }
        }
        return positions;
    }


    /**
     *转化为职位类型数据
     * （递归出所有的子节点）
     *
     * @param bossBaseVos
     * @param positionList
     */
    private static void handlePositions(List<BossBaseVo> bossBaseVos,List<Positions> positionList, String dataVer,long rootId,long parentId) {
        if (!CollectionUtils.isEmpty(bossBaseVos)) {
            for (BossBaseVo vo : bossBaseVos) {
                long jobsIntId = IDUtils.getJobsIntId();
                Positions positions = new Positions();
                positionList.add(positions);
                positions.setDateVersion(dataVer);
                positions.setInclude(WebsiteEnum.BOSS.getWebCode());
                positions.setPositionName(vo.getName());
                positions.setPositionUrl(vo.getCode());
                if (rootId == 0 && parentId == 0) {
                    rootId = jobsIntId;
                    positions.setLeaf(Boolean.FALSE);
                }
                positions.setRootId(rootId);
                positions.setId(jobsIntId);
                positions.setParentId(parentId);
                if (!CollectionUtils.isEmpty(vo.getSubLevelModelList())) {
                    handlePositions(vo.getSubLevelModelList(), positionList, dataVer, rootId, jobsIntId);
                }
            }
        }
    }

    public static List<City>  getHotCity(String sourceJson){
        List<City> citys = new ArrayList<>();
        if(StringUtils.isNotEmpty(sourceJson)) {
            try {
                JsonElement jsonElement = new JsonParser().parse(sourceJson);
                String targetJson = jsonElement.getAsJsonObject().get("data").getAsJsonObject().get("hotCityList").toString();
                if (StringUtils.isNotEmpty(targetJson)) {
                    List<BossBaseVo> bossBaseVos = JSON.parseArray(targetJson, BossBaseVo.class);
                    String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
                    handleCity(bossBaseVos, citys, dateVersion);
                }
            }catch (Exception e){
                logger.info("get boss hot city info faild！",e);
            }
        }
        return citys;
    }
    public static List<City>  getCity(String sourceJson){
        List<City> citys = new ArrayList<>();
        if(StringUtils.isNotEmpty(sourceJson)) {
            try {
                JsonElement jsonElement = new JsonParser().parse(sourceJson);
                String targetJson = jsonElement.getAsJsonObject().get("data").getAsJsonObject().get("cityList").toString();
                if (StringUtils.isNotEmpty(targetJson)) {
                    List<BossBaseVo> bossBaseVos = JSON.parseArray(targetJson, BossBaseVo.class);
                    String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
                    handleCity(bossBaseVos, citys, dateVersion);
                }
            }catch (Exception e){
                logger.info("boss 城市信息获取失败！",e);
            }
        }
        return citys;
    }

    private static void handleCity(List<BossBaseVo> baseVos,List<City> citys,String dateVer){
        if(!CollectionUtils.isEmpty(baseVos)){
            for(BossBaseVo vo :baseVos){
                City city = new City();
                citys.add(city);
                city.setCityName(vo.getName());
                city.setDateVersion(dateVer);
                city.setInclude(WebsiteEnum.BOSS.getWebCode());
                city.setCode(vo.getCode());
            }
        }
    }

    public static  List<PositionDetail> getBossPositionDetails(String html){
        List<PositionDetail> positionDetails = new ArrayList<>();
        if(StringUtils.isNotEmpty(html)) {
            Document bossPositionDetails = Jsoup.parse(html);
            Elements jobList = bossPositionDetails.getElementsByClass("job-list");
            for(int i = 0;i<jobList.size();i++){
                Elements jobPrimary = jobList.get(i).getElementsByClass("job-primary");
                for(int j= 0;j <jobPrimary.size();j++){
                    Element element = jobPrimary.get(j);
                    PositionDetail positionDetail = new PositionDetail();
                    positionDetails.add(positionDetail);
                    positionDetail.setCity(bossPositionDetails.getElementsByClass("disabled").text());
                    handlePositionDetails(element,positionDetail);
                }
            }
        }
        return positionDetails;
    }

    private static  void handlePositionDetails(Element element,PositionDetail positionDetail){
        Elements infoPrimary = element.getElementsByClass("info-primary");
        Elements infoCompany = element.getElementsByClass("info-company");
        Elements infoPublish =element.getElementsByClass("info-publis");
        Element infoP = infoPrimary.get(0);
        String positioNname = infoP.getElementsByClass("job-title").text();
        String salary = infoP.getElementsByTag("h3").get(0).getElementsByClass("red").text();
        String positionUrl = infoP.getElementsByTag("h3").get(0).getElementsByTag("a").get(0).attr("href").toString();
        String[] cityWordEduction = infoP.getElementsByTag("p").last().toString().replace("<p>", "").replace("</p>", "").split("<em class=\"vline\"></em>");

        Elements h3Company = infoCompany.get(0).getElementsByTag("h3");
        String companyName = h3Company.get(0).getElementsByTag("a").text();
        String[] companyInfo = infoCompany.get(0).getElementsByTag("p").toString().replace("<p>", "").replace("</p>", "").split("<em class=\"vline\"></em>");
        positionDetail.setPositionName(positioNname);
        String tags = infoP.getElementsByClass("tags").text();
        if(StringUtils.isNotBlank(salary)){
            String[] split = salary.replace("k", "").replace("K", "").replace("以上","").replace("不限", "").split("-");
            try {
                if (split.length == 0) {
                    positionDetail.setMaxSalary(new BigDecimal(0));
                    positionDetail.setMinSalary(new BigDecimal(0));
                } else if (split.length == 1) {
                    positionDetail.setMaxSalary(new BigDecimal(split[0]));
                    positionDetail.setMinSalary(new BigDecimal(split[0]));
                } else {
                    positionDetail.setMaxSalary(new BigDecimal(split[1]));
                    positionDetail.setMinSalary(new BigDecimal(split[0]));
                }
            }catch (Exception e){
                logger.info("薪资填充数据失败!!",e);
            }
        }
        if(cityWordEduction.length ==3){
            positionDetail.setDistrict(cityWordEduction[0]);
            if(cityWordEduction[1] != null){
                try {

                    String[] split = cityWordEduction[1].replace("年", "").replace("以上", "").replace("以内", "").replace("少于", "").replace("不限", "").split("-");
                    if (split.length == 0 || "经验不限".equals(split[0]) || "应届生".equals(split[0])) {
                        positionDetail.setWorkMinYear(0);
                        positionDetail.setWorkMaxYear(0);
                    } else if (split.length == 1 && !"经验不限".equals(split[0]) && !"应届生".equals(split[0])) {
                        positionDetail.setWorkMinYear(Integer.parseInt(split[0]));
                        positionDetail.setWorkMaxYear(Integer.parseInt(split[0]));
                    } else {
                        positionDetail.setWorkMinYear(Integer.parseInt(split[0]));
                        positionDetail.setWorkMaxYear(Integer.parseInt(split[1]));
                    }
                }catch (Exception e){
                    logger.error("工作年限填充数据失败!",e);
                }
            }
            positionDetail.setEducation(cityWordEduction[2]);
        }
        positionDetail.setCompanyShortName(companyName);
        positionDetail.setCompanyFullName(companyName);
        if(companyInfo.length == 3){
            positionDetail.setIndustryField(companyInfo[0]);
            positionDetail.setFinanceStage(companyInfo[1]);
            try {
                String[] split = companyInfo[2].replace("人", "").replace("以上", "").replace("以内", "").replace("不限", "").replace("少于", "").replace("以上", "").split("-");
                if (split.length == 0) {
                    positionDetail.setCompanyMinSize(0);
                    positionDetail.setCompanyMaxSize(0);
                } else if (split.length == 1) {
                    positionDetail.setCompanyMinSize(Integer.parseInt(split[0]));
                    positionDetail.setCompanyMaxSize(Integer.parseInt(split[0]));
                } else {
                    positionDetail.setCompanyMinSize(Integer.parseInt(split[0]));
                    positionDetail.setCompanyMaxSize(Integer.parseInt(split[1]));
                }
            }catch (Exception e){
                logger.error("公司人数填充数据失败!!",e);
            }
        }
        positionDetail.setPositionLables(tags);
        positionDetail.setInclude(WebsiteEnum.BOSS.getWebCode());
    }


}
