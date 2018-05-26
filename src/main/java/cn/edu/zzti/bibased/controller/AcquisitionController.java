package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.ResultMap;
import cn.edu.zzti.bibased.service.operation.boss.BossGetService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouGetService;
import cn.edu.zzti.bibased.service.operation.other.ActionLogService;
import cn.edu.zzti.bibased.service.operation.zhilian.ZhilianGetService;
import org.springframework.http.converter.json.MappingJacksonValue;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 信息采集
 */
@RestController
@RequestMapping("/acquisition")
public class AcquisitionController {

    @Resource
    private LagouGetService lagouGetService;
    @Resource
    private BossGetService bossGetService;

    @Resource
    private ActionLogService actionLogService;
    @Resource
    private ZhilianGetService zhilianGetService;

    @RequestMapping(value = "/v1/company_search/{webCode}")
    public Object companyOperation(@PathVariable("webCode") String code){
        lagouGetService.getCompanyInfomationV2();
        //jsonp包装
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new ResultMap());
        mappingJacksonValue.setJsonpFunction("callback");
        return mappingJacksonValue;
    }


    @RequestMapping(value = "/v1/get_company/{webCode}")
    public Object getCompanyInfo(@PathVariable("webCode") String code){
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        lagouGetService.getCompanyInfomationV2();
                        break;
                    case BOSS:
                        System.out.printf("boss");
                        break;
                }
            }
        }
        return this.result();
    }
    @RequestMapping(value = "/v1/get_city/{webCode}")
    public Object getCityInfo(@PathVariable("webCode") String code){
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        lagouGetService.getCityInformation();
                        break;
                    case BOSS:
                        bossGetService.getCity();
                        break;
                }
            }
        }
        return this.result();
    }
    @RequestMapping(value = "/v1/get_positions/{webCode}")
    public Object getPositionsInfo(@PathVariable("webCode") String code){
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        lagouGetService.getJobInformation();
                        break;
                    case BOSS:
                        bossGetService.getBossPositionTypeV2();
                        break;
                    case ZHILIAN:
                        zhilianGetService.getPositionType();
                        break;
                }
            }
        }
        return this.result();
    }
    @RequestMapping(value = "/v1/get_position_details/{webCode}")
    public Object getPositionDetailsInfo(@PathVariable("webCode") String code){
        for (WebsiteEnum websiteEnum:WebsiteEnum.values()){
            if(websiteEnum.getWebCode().equals(code)) {
                switch (websiteEnum) {
                    case LAGOU:
                        lagouGetService.getPositionDeailsInfomation();
                        break;
                    case BOSS:
                        bossGetService.getPositionDetails();
                        break;
                    case ZHILIAN:
                        zhilianGetService.getPosiotnDetails();
                        break;
                }
            }
        }
        return this.result();
    }

    /**
     * 进度
     *
     * @param time
     * @return
     */
    @RequestMapping(value = "/v1/get_Speed_of_progress")
    public Object getSpeedOfProgress(Integer time){
        if(time == null){
            time = 100;
        }
        int newTime=(int)(Math.random()*5)+time;
        return this.result(newTime >100 ?100:newTime);
    }

    /**
     * 进度
     *
     * @param time
     * @return
     */
    @RequestMapping(value = "/v1/boss")
    public Object getBoss(Integer time){
        this.bossGetService.getBossPositionType();
        return this.result(100);
    }

    /**
     * 项目的详情
     *
     * @return
     */
    @RequestMapping(value = "/v1/get_project_detail")
    public Object getProjectDetail(String webType,Integer typeCode){

        actionLogService.queryActionLog(webType,typeCode);

        return this.result(100);
    }
    private Object result(int data){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new ResultMap(data));
        mappingJacksonValue.setJsonpFunction("callback");
        return mappingJacksonValue;
    }
    private Object result(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new ResultMap());
        mappingJacksonValue.setJsonpFunction("callback");
        return mappingJacksonValue;
    }
}
