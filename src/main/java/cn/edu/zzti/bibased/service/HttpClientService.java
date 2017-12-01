package cn.edu.zzti.bibased.service;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
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

    @Resource
    private CloseableHttpClient httpsClient;
    @Resource
    private RequestConfig requestConfig;
    @Resource
    private BasicCookieStore cookieStore;

    public String doGet(String apiUrl,Map<String, String> parapms) throws Exception {
        URI uri = null;
        if (null == parapms) {
            uri = URI.create(apiUrl);
        } else {
            // 设置参数
            URIBuilder builder = new URIBuilder(apiUrl);
            for (Map.Entry<String, String> entry : parapms.entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
            uri = builder.build();
        }
        String html = "";
        HttpGet request = new HttpGet(uri);
        for (Map.Entry<String, String> entry : HttpHeaderConstant.lagouHeader.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse response = null;
        try {
            request.setConfig(requestConfig);
            response = httpsClient.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String charset = getContentCharSet(entity);
                HttpEntity mEntity = response.getEntity();
                html = EntityUtils.toString(mEntity,charset);
            }
            List<Cookie> cookies = cookieStore.getCookies();
            if(!CollectionUtils.isEmpty(cookies)){

            }
        }catch(IOException e){
            logger.error("http service error!!"+e);
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
     * 执行POST请求
     */
    public String doPost(String apiUrl, Map<String, Object> params) throws Exception {
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        String html = null;

        try {
            httpPost.setConfig(requestConfig);
            for (Map.Entry<String, String> entry : HttpHeaderConstant.lagouHeader.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
            List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                        .getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
            response = httpsClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            html = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
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
