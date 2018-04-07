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
                    Element element = jobList.get(j);
                    Elements infoPrimary = element.getElementsByClass("info-primary");
                    Elements infoCompany = element.getElementsByClass("info-company");
                    Elements infoPublish =element.getElementsByClass("info-publis");
                    Element infoP = infoPrimary.get(0);
                    String thirdType = infoP.getElementsByClass("job-title").toString();
                    String salary = infoP.getElementsByTag("h3").get(0).getElementsByTag("span").text();
                    String positionUrl = infoP.getElementsByTag("h3").get(0).getElementsByTag("a").get(0).getElementsByAttribute("href").toString();
                    String address = infoP.getElementsByTag("p").text();
                    Elements h3Company = infoCompany.get(0).getElementsByTag("h3");
                    String companyName = h3Company.get(0).getElementsByTag("a").text();
                    String companyInfo = h3Company.get(0).getElementsByTag("p").text();

                }
            }
        }
        return positionDetails;
    }
}
