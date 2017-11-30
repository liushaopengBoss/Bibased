package cn.edu.zzti.bibased.service;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.utils.SSLContextClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import java.net.URI;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class HttpClientService {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired
    private CloseableHttpClient httpClient;


    public String doGet(String url,Map<String, String> parapms) throws Exception {
        URI uri = null;
        if (null == parapms) {
            uri = URI.create(url);
        } else {
            // 设置参数
            URIBuilder builder = new URIBuilder(url);
            for (Map.Entry<String, String> entry : parapms.entrySet()) {
                builder.addParameter(entry.getKey(), entry.getValue());
            }
            uri = builder.build();
        }
        httpClient = SSLContextClient.getNewHttpsClient(httpClient);
        String html = "";
        HttpGet request = new HttpGet(uri);
        for (Map.Entry<String, String> entry : HttpHeaderConstant.lagouHeader.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String charset = getContentCharSet(entity);
                HttpEntity mEntity = response.getEntity();
                html = EntityUtils.toString(mEntity,charset);
            }
        }catch(IOException e){
            logger.error("http service error!!"+e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 执行POST请求
     */
    public String doPost(String url) throws Exception {
        return "";
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
