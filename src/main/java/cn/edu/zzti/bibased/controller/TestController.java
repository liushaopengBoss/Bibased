package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.dao.TestMapper;
import cn.edu.zzti.bibased.pojo.PojoTest;
import cn.edu.zzti.bibased.service.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        String postUrl = "https://www.lagou.com/jobs/positionAjax.json?px=default&city=北京&needAddtionalResult=false&isSchoolJob=0";
        try{
            url= httpClientService.doGet(url, null);

            Map<String,Object> param = new HashMap<>();
            param.put("first",true);
            param.put("pn",1);
            param.put("kd","Java");
            //url = httpClientService.doPost(postUrl, param);
        }catch (Exception e){
            logger.error("ertr");
            e.printStackTrace();
        }

        //testMapper.insert(new PojoTest(UUID.randomUUID().toString(),"456456456"));
             return  url;
    }

}
