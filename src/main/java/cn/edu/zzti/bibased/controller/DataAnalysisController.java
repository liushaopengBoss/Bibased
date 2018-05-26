package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.service.operation.boss.BossQueryService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouQueryService;
import cn.edu.zzti.bibased.service.operation.zhilian.ZhilianQueryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据分析服务
 *
 * Created by huaidou on  2018/3/7
 */
@RestController
@RequestMapping("/rest")
public class DataAnalysisController {

    @Resource
    private LagouQueryService lagouQueryService;
    @Resource
    private BossQueryService bossQueryService;
    @Resource
    private ZhilianQueryService zhilianQueryService;


    public DataAnalysisController() {
    }

    /**
     * 每个城市的公司数量
     * @return
     */
    @RequestMapping("/v1/queryCityCompanNum")
    @ResponseBody
    public List<Company> queryCityCompanNum(){
        return lagouQueryService.queryCityCompanNum();
    }

    /**
     * 全国公司融资情况图 和 职位类型数据
     * @return
     */
    @RequestMapping("/v1/queryFinanceStageCompanNum")
    @ResponseBody
    public  List<Company> queryFinanceStageCompanNum(){
        return lagouQueryService.queryFinanceStageCompanNum();
    }

    @RequestMapping("/v1/queryIndustryCompanNum")
    @ResponseBody
    public List<Company> queryIndustryCompanNum(){
        return lagouQueryService.queryIndustryCompanNum();
    }

    /**
     * 职位类型数据
     * @return
     */
    @RequestMapping("/v1/queryPositionTypeNums")
    @ResponseBody
    public List<Positions> queryPositionTypeNums(){
        return lagouQueryService.queryPositionTypeNums();
    }

    /**
     * 总职位数量
     * @return
     */
    @RequestMapping("/v1/queryPositionDetailsByCount")
    @ResponseBody
    public int queryPositionDetailsByCount(){
        return lagouQueryService.queryPositionDetailsByCount();
    }

    /**
     * 总职位数量
     * @return
     */
    @RequestMapping("/v1/queryCompanyDetailsByCount")
    @ResponseBody
    public int queryCompanyDetailsByCount(){
        return lagouQueryService.queryCompanyDetailsByCount();
    }

    /**
     * 职位类型数据
     *
     * @return
     */
    @RequestMapping("/v1/queryWorkYearNums/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryWorkYearNums(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        positionDetails =  lagouQueryService.queryWorkYearNums();
                        break;
                    case BOSS:
                        positionDetails =  bossQueryService.queryWorkYearNums();
                        break;
                    case ZHILIAN:
                        positionDetails =  zhilianQueryService.queryWorkYearNums();
                        break;
                }
            }
        }
        return positionDetails;
    }

    /**
     * 职位类型数据
     *
     * @return
     */
    @RequestMapping("/v1/queryEducationNums/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryEducationNums(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        positionDetails = lagouQueryService.queryEducationNums();
                        break;
                    case BOSS:
                        positionDetails =  bossQueryService.queryEducationNums();
                        break;
                    case ZHILIAN:
                        positionDetails = zhilianQueryService.queryEducationNums();
                        break;
                }
            }
        }
        return positionDetails;
    }
    /**
     * 职位类型数据
     *
     * @return
     */
    @RequestMapping("/v1/queryJobNatureNums/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryJobNatureNums(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        positionDetails =  lagouQueryService.queryJobNatureNums();
                        break;
                    case BOSS:
                        positionDetails =  bossQueryService.queryJobNatureNums();
                        break;
                    case ZHILIAN:
                        positionDetails = zhilianQueryService.queryJobNatureNums();
                        break;
                }
            }
        }
        return positionDetails;
    }
    /**
     * 拉钩技术类型下 各种职位类型的职位数量
     *
     * @return
     */
    @RequestMapping("/v1/queryPositionDetailsByJS/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryPositionDetailsByFirstTye(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        positionDetails =  lagouQueryService.queryPositionDetailsByFirstTye("技术");
                        break;
                    case BOSS:
                        positionDetails =  bossQueryService.queryPositionDetailsByFirstTye("技术");
                        break;
                    case ZHILIAN:
                        positionDetails =  zhilianQueryService.queryPositionDetailsByFirstTye("技术");
                        break;
                }
            }
        }
        return positionDetails;
    }
    /**
     * 拉钩  不同公司规模的职位数量
     *
     * @return
     */
    @RequestMapping("/v1/queryCompanySize/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryCompanySize(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        positionDetails =  lagouQueryService.queryCompanySize();
                        break;
                    case BOSS:
                        positionDetails =  bossQueryService.queryCompanySize();
                        break;
                }
            }
        }
        return positionDetails.stream().filter(po->{
            return po.getNum()>0;
        }).collect(Collectors.toList());
    }
    /**
     * 获取每个招聘网站在各个城市的职位数量
     *
     * @return
     */
    @RequestMapping("/v1/queryWebCityNums")
    @ResponseBody
    public Map<String,List<PositionDetail>> queryWebCityNums(){
        return lagouQueryService.queryWebCityNums();
    }

    /**
     * 不同行业中的职位数量
     * @return
     */
    @RequestMapping("/v1/queryIndustryFieldNums/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryIndustryFieldNums(@PathVariable("webCode") String code){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                positionDetails =  bossQueryService.queryIndustryFieldNums(websiteEnum.getWebCode());
            }
        }
        return positionDetails;
    }


    /**
     * 不同行业中的职位数量
     * @return
     */
    @RequestMapping("/v1/queryDifferentSalaryNum/{webCode}")
    @ResponseBody
    public List<PositionDetail> queryDifferentSalaryNum(@PathVariable("webCode") String code ){
        List<PositionDetail> positionDetails = new ArrayList<>();
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                    positionDetails = lagouQueryService.queryDifferentSalaryNum(websiteEnum.getWebCode());
            }
        }
        return positionDetails;
    }



}
