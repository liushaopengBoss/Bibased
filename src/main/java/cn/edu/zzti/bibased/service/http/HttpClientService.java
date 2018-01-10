package cn.edu.zzti.bibased.service.http;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import org.apache.http.*;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import java.io.IOException;

@Service
public class HttpClientService {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);
    /**
     * 注入HTTPS
     */
    @Resource
    private CloseableHttpClient httpsClient;
    /**
     * 注入HTTP
     */
    @Resource
    private CloseableHttpClient httpClient;

    @Resource
    private RequestConfig requestConfig;
    @Resource
    private BasicCookieStore cookieStore;

    /**
     * get请求
     * @param apiUrl   url
     * @param params   参数
     * @return
     * @throws Exception
     */
    public String doGet(String apiUrl,Map<String, Object> params,Map<String, Object> headers)  {
        URI uri = null;
        if (null == params) {
            uri = URI.create(apiUrl);
        } else {
            try{
                // 设置参数
                URIBuilder builder = new URIBuilder(apiUrl);
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    builder.addParameter(entry.getKey(), entry.getValue().toString());
                }
                uri = builder.build();
            }catch (Exception e){
                logger.error("https get参数设置一样："+e);
            }
        }
        String html = "";
        CloseableHttpResponse response = null;
        HttpGet httpGet = new HttpGet(uri);
        try {
            for (Map.Entry<String, Object> entry : HttpHeaderConstant.lagouGetHeader.entrySet()) {
                httpGet.addHeader(entry.getKey(), entry.getValue().toString());
            }
            httpGet.setConfig(requestConfig);
            if(uri.toString().contains("https://")){
                response = httpsClient.execute(httpGet);
            }else{
                response = httpClient.execute(httpGet);
            }
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //获取返回数据
                HttpEntity entity = response.getEntity();
                String charset = getContentCharSet(entity);
                HttpEntity mEntity = response.getEntity();
                html = EntityUtils.toString(mEntity,charset);
                //获取header头
                // Set-Cookie: SEARCH_ID=1b772ae7995c4065ba144eeea6d02636; Version=1; Max-Age=86400; Expires=Tue, 05-Dec-2017 05:37:10 GMT; Path=/
                Header[] resultHeaders = response.getHeaders("Set-Cookie");
                resultHeaders[0].getValue();
            }else{
                httpGet.abort();
            }
            List<Cookie> cookies = cookieStore.getCookies();
            if(!CollectionUtils.isEmpty(cookies)){

            }
        }catch(IOException e){
            httpGet.abort();
            logger.error("https get请求失败：uri:"+apiUrl+"\n"+e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return html;
    }

    /**
     *  post请求
     * @param apiUrl   url
     * @param params   参数
     * @param headers  header头
     * @return
     */
    public String doPost(String apiUrl, Map<String, Object> params,Map<String, Object> headers)  {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String html = null;
        try {
            for (Map.Entry<String, Object> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue().toString());
            }
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            httpPost.setConfig(requestConfig);
            if(apiUrl.toString().contains("https://")){
                response = httpsClient.execute(httpPost);
            }else{
                response = httpClient.execute(httpPost);
            }
            response = httpsClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                httpPost.abort();
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            html = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            httpPost.abort();
            logger.error("post请求异常：uri:"+apiUrl+"\n异常信息"+e);
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return html;
    }

    private static String getContentCharSet(final HttpEntity entity) {

        if (entity == null) {
            throw new IllegalArgumentException("HTTP entity may not be null");
        }
        String charset = null;
        if (entity.getContentType() != null) {
            HeaderElement[] values = entity.getContentType().getElements();
            if (values.length > 0) {
                org.apache.http.NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }

        if(StringUtils.isEmpty(charset)){
            charset = "UTF-8";
        }
        return charset;
    }
}
