package cn.edu.zzti.bibased.thread;

import cn.edu.zzti.bibased.service.HttpClientService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.Callable;

/**
 * 拉钩网的获取数据线程
 * 有返回值的
 */

public class LaGouTask implements Callable {
   final private String apiUrl;
    @Resource
    private HttpClientService HttpClientService;

    public LaGouTask(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public Object call() throws Exception {

        return null;
    }
}
