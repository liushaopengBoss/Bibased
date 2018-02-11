package cn.edu.zzti.bibased.thread;

import cn.edu.zzti.bibased.service.http.HttpClientService;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 多线程抽象执行器类
 * create by huaidou on 2018/02/09
 */
public abstract class BaseExecuter implements AnsyTask.Executer {
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
    @Resource
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
}
