package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.aspect.ActionLog;
import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.dto.ResultMap;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import cn.edu.zzti.bibased.service.operation.other.ActionLogService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rest")
public class GetInfomationController{

    @Resource
    private LagouService lagouService;

    @Resource
    private ActionLogService actionLogService;

    @RequestMapping(value = "/v1/company_search")
    public Object companyOperation(String code){
        lagouService.getCompanyInfomationV2();
        //jsonp包装
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new ResultMap());
        mappingJacksonValue.setJsonpFunction("callback");
        return mappingJacksonValue;
    }


    @RequestMapping(value = "/v1/get_company")
    public Object getCompanyInfo(String code){
        lagouService.getCompanyInfomationV2();
        return this.result();
    }
    @RequestMapping(value = "/v1/get_city")
    public Object getCityInfo(String code){
        lagouService.getCityInformation();
        return this.result();
    }
    @RequestMapping(value = "/v1/get_positions")
    public Object getPositionsInfo(String code){
        lagouService.getJobInformation();
        return this.result();
    }
    @RequestMapping(value = "/v1/get_position_details")
    public Object getPositionDetailsInfo(String code){
        lagouService.getPositionDeailsInfomation();
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
