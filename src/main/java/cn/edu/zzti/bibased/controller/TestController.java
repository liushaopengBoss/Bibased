package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.dao.TestMapper;
import cn.edu.zzti.bibased.pojo.PojoTest;
import cn.edu.zzti.bibased.service.HttpClientService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
public class TestController {
//    @Resource
//    TestMapper testMapper;
    @Resource
    private HttpClientService httpClientService;
    @RequestMapping("/hello")
    public String ab(){
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        try{
            url= httpClientService.doGet(url, null);
        }catch (Exception e){
            e.printStackTrace();
        }

        //testMapper.insert(new PojoTest(UUID.randomUUID().toString(),"456456456"));
             return  url;
    }

}
