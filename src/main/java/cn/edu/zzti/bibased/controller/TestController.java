package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.HttpType;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);
    @Resource
    private LagouService lagouService;

    @RequestMapping("/hello")
    public String ab(){

        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String postUrl = "https://www.lagou.com/jobs/positionAjax.json?px=default&city=%E5%8C%97%E4%BA%AC&needAddtionalResult=false&isSchoolJob=0";
        try{
            Map<String,Object> param = new HashMap<>();
            param.put("kd","Java");
            param.put("pn",1);
            param.put("first",true);
           url = lagouService.startGetDate(postUrl,param, HttpType.POST);
        }catch (Exception e){
            logger.error("error"+e.getMessage());
            e.printStackTrace();
        }
        return  url;
    }

    @RequestMapping("/jobs")
    public String jobs(){
        lagouService.initLagouInfo();
        return "success";
    }
    @RequestMapping("/position")
    public String position(){
        lagouService.getCompanyInfomationV2();
        return "success";
    }


}
