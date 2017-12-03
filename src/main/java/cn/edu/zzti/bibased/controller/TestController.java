package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.service.HttpClientService;
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
//    @Resource
//    TestMapper testMapper;
    @Resource
    private HttpClientService httpClientService;
    @RequestMapping("/hello")
    public String ab(){
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String postUrl = "https://www.lagou.com/jobs/positionAjax.json?px=default&city=%E5%8C%97%E4%BA%AC&needAddtionalResult=false&isSchoolJob=0";
        try{
           url= httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
            Map<String,Object> param = new HashMap<>();
            param.put("kd","Java");
            param.put("pn",1);
            param.put("first",true);
           url= httpClientService.doPost(postUrl, param,HttpHeaderConstant.lagouAjaxHeader);
        }catch (Exception e){
            logger.error("error"+e);
            e.printStackTrace();
        }

        //testMapper.insert(new PojoTest(UUID.randomUUID().toString(),"456456456"));
             return  url;
    }

}
