package cn.edu.zzti.bibased.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Future;

@Service
public class LagouService {

    @Resource
    private CompletionService<Future> ompletionService;
    @Resource
    private HttpClientService httpClientService;

    public String startGetDate(){
        return  "";
    }
}
