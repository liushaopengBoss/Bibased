package cn.edu.zzti.bibased.execute;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.thread.AnsyTask;
import cn.edu.zzti.bibased.utils.SpringContextUtils;

import javax.annotation.Resource;
import java.util.Map;
/**
 * 多线程抽象执行器类
 *
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
    /**
     * 网站类型
     */
    protected WebsiteEnum websiteEnum;

    protected int positionId;
    @Resource
    protected HttpClientService httpClientService;

    @Override
    public Object executer() throws Exception {
        return builderResult();
    }

   //抽象的获取数据方法
    /**
     * 获取数据方法
     *
     * @param <T>
     * @return
     */
    protected abstract  <T> T  builderResult();

    /**
     * 获取页面数
     *
     * @return
     */
    public  int getPageSize(){
        return 0;
    }
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

    public WebsiteEnum getWebsiteEnum() {
        return websiteEnum;
    }

    public void setWebsiteEnum(WebsiteEnum websiteEnum) {
        this.websiteEnum = websiteEnum;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }
}
