package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.aspect.ActionLog;
import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.dto.ResultMap;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GetInfomationController{

    @Resource
    private LagouService lagouService;

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
    private Object result(){
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(new ResultMap());
        mappingJacksonValue.setJsonpFunction("callback");
        return mappingJacksonValue;
    }
}
