package cn.edu.zzti.bibased.controller;

import cn.edu.zzti.bibased.dao.TestMapper;
import cn.edu.zzti.bibased.pojo.PojoTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RestController
public class TestController {
    @Resource
    TestMapper testMapper;

    @RequestMapping("/hello")
    public List<PojoTest> ab(){

        testMapper.insert(new PojoTest(UUID.randomUUID().toString(),"456456456"));
        return  testMapper.get();
    }

}
