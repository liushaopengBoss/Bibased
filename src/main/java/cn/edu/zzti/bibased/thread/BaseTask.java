package cn.edu.zzti.bibased.thread;

import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.utils.SpringContextUtils;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 多线程抽象类
 * create by huaidou on 2018/02/09
 */
public abstract class BaseTask  implements Callable {
    /**
     * 请求头
     */
    protected Map<String,Object> headers;
    /**
     * Url
     */
    protected String apiUrl;
    /**
     * post参数
     */
    protected  Map<String,Object> params;

    protected HttpClientService httpClientService;

    @Override
    public Object call() throws Exception {
        return builderResult();
    }

    /**
     * 抽象的获取数据方法
     * @param <T>
     * @return
     */
    protected abstract  <T> T  builderResult();


    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }


    public void setHttpClientService(HttpClientService service) {
        HttpClientService httpClientService =(HttpClientService) SpringContextUtils.getBean("httpClientService");
        this.httpClientService = httpClientService;
    }
}
