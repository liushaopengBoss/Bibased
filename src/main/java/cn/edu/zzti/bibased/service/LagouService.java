package cn.edu.zzti.bibased.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LagouService {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Resource
    private HttpClientService httpClientService;

    public String startGetDate(){
        return  "";
    }
}
