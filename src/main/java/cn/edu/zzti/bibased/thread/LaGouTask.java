package cn.edu.zzti.bibased.thread;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.constant.HttpType;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.utils.SpringContextUtils;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 拉钩网的获取数据线程
 * 有返回值的
 * 不建议使用
 *
 */
public class LaGouTask implements Callable {
    private String apiUrl; //url
    private Map<String,Object> param;//参数
    private String httpType;//请求类型
    public LaGouTask(String apiUrl, Map<String,Object> param,String httpType) {
        this.apiUrl = apiUrl;
        this.param = param;
        this.httpType = httpType;
    }

    @Override
    public String call() throws Exception {
        HttpClientService httpClientService =(HttpClientService) SpringContextUtils.getBean("httpClientService");
        String data = null;
        switch (httpType){
            case HttpType.GET:data =  httpClientService.doGet(apiUrl, param, HttpHeaderConstant.lagouGetHeader);break;
            case HttpType.POST:data =  httpClientService.doPost(apiUrl, param, HttpHeaderConstant.lagouAjaxHeader);break;
        }
        return data;
    }
}
