package cn.edu.zzti.bibased.service.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HttpClient 基类
 */
public class BaseHttpClientHandler {
    @Resource
    protected CloseableHttpClient httpClient;

    public String post(String url,Map<String, Object> form, String headerAcceptCharset) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        if (StringUtils.isNotEmpty(headerAcceptCharset)) {
            httpPost.setHeader("Accept-Charset", headerAcceptCharset);
        }
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        Map<String,String> params=new HashMap<>();
        for(String key:form.keySet()){
            nvps.add(new BasicNameValuePair(key, String.valueOf(form.get(key)))); // 参数
        }
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
        httpPost.setEntity(formEntity); // 设置参数给Post
        return getResponseContent(url, headerAcceptCharset, httpPost);
    }

    public String get(String url, String headerAcceptCharset) throws Exception {
        HttpGet get = new HttpGet(url);
        return getResponseContent(url, headerAcceptCharset, get);
    }

    private String getResponseContent(String url, String headerAcceptCharset, HttpRequestBase request) throws Exception {
        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
            return EntityUtils.toString(response.getEntity(), headerAcceptCharset);
        } catch (Exception e) {
            throw new Exception("got an error from HTTP for url : " + URLDecoder.decode(url, "UTF-8"), e);
        } finally {
            if (response != null) {
                EntityUtils.consumeQuietly(response.getEntity());
            }
            request.releaseConnection();
        }
    }
}
