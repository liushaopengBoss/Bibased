package cn.edu.zzti.bibased.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huaidou on  2018/3/27
 */
@RestController
@RequestMapping("/rest")
public class GetInfoController {


    @Value("${http.request.connectionRequestTimeout}")
    private int abc;
    @RequestMapping("/v1/queryabc")
    public void abc(){
        System.out.printf(""+abc);
    }
}
