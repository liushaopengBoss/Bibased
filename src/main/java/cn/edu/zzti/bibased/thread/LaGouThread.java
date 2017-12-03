package cn.edu.zzti.bibased.thread;

import cn.edu.zzti.bibased.service.HttpClientService;

import java.util.concurrent.Callable;

/**
 * 拉钩网的获取数据线程
 * 有返回值的
 */
public class LaGouThread implements Callable {
    private String apiUrl;
    private HttpClientService HttpClientService;

    public LaGouThread(String apiUrl, HttpClientService httpClientService) {
        this.apiUrl = apiUrl;
        HttpClientService = httpClientService;
    }

    @Override
    public Object call() throws Exception {

        return null;
    }
}
