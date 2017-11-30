package cn.edu.zzti.bibased.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

public class MyHttpClientUtils {

    private static final String METHOD_POST = "POST";
    private static final String DEFAULT_CHARSET = "utf-8";
    /**
     * 获取网页html   get
     */
    public static String doHttpCilentGet(String currentUrl){
        HttpClient httpClient=new DefaultHttpClient();
        if("https://".contains(currentUrl)) {
            httpClient = SSLContextClient.getNewHttpsClient(httpClient);
        }
        String html = "";
        HttpGet request = new HttpGet(currentUrl);
       request.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
       request.addHeader("Accept-Language","zh-CN,zh;q=0.9");
       request.addHeader("Cache-Control","max-age=0");
       request.addHeader("Connection","keep-alive");
     // request.addHeader("Cookie","guid=15116927404959480079; search=jobarea%7E%60080200%7C%21ord_field%7E%600%7C%21recentSearch0%7E%601%A1%FB%A1%FA080200%2C00%A1%FB%A1%FA000000%A1%FB%A1%FA0000%A1%FB%A1%FA00%A1%FB%A1%FA9%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FA99%A1%FB%A1%FAjava%A1%FB%A1%FA2%A1%FB%A1%FA%A1%FB%A1%FA-1%A1%FB%A1%FA1511692762%A1%FB%A1%FA0%A1%FB%A1%FA%A1%FB%A1%FA%7C%21; nsearch=jobarea%3D%26%7C%26ord_field%3D%26%7C%26recentSearch0%3D%26%7C%26recentSearch1%3D%26%7C%26recentSearch2%3D%26%7C%26recentSearch3%3D%26%7C%26recentSearch4%3D%26%7C%26collapse_expansion%3D; adv=adsnew%3D0%26%7C%26adsresume%3D1%26%7C%26adsfrom%3Dhttp%253A%252F%252Fbzclk.baidu.com%252Fadrc.php%253Ft%253D06KL00c00fZEOkb0yvNu00uiAsalhwGp000000RnxNb00000xgB-WZ.THYdnyGEm6K85yF9pywd0ZnqrAfvPhPWnhmsnj0YPhuhn6Kd5HuDP1cYrHNAPDD1PYfsnRcsrRckfYf1wHTsn1m1nW6k0ADqI1YhUyPGujY1njR4PjTvnjmkFMKzUvwGujYkP6K-5y9YIZ0lQzqYTh7Wui3dnyGEmB4WUvYEIZF9mvR8TA9s5v7bTv4dUHYLrjbzn1nhmyGs5y7cRWKWwAqvHjPbnvw4Pj7PNLKvyybdphcznZufn-G4mWcsrN-VwMKpi7uLuyTq5iuo5HK-nHRzPjfzuj9Bm1bdnARdrHuBm1fvnH-WuWbsuhuB0APzm1YkrH6LPf%2526tpl%253Dtpl_10085_15730_11224%2526l%253D1501367301%2526attach%253Dlocation%25253D%252526linkName%25253D%252525E6%252525A0%25252587%252525E9%252525A2%25252598%252526linkText%25253D%252525E5%25252589%2525258D%252525E7%252525A8%2525258B%252525E6%25252597%252525A0%252525E5%252525BF%252525A7%252851Job%2529-%252525E6%25252589%252525BE%252525E5%252525B7%252525A5%252525E4%252525BD%2525259C%252525E5%252525B0%252525BD%252525E5%2525259C%252525A8%252525E5%25252589%2525258D%252525E7%252525A8%2525258B%252525E6%25252597%252525A0%252526xp%25253Did%2528%25252522m4935ee28%25252522%2529%2525252FDIV%2525255B1%2525255D%2525252FDIV%2525255B1%2525255D%2525252FDIV%2525255B1%2525255D%2525252FDIV%2525255B1%2525255D%2525252FH2%2525255B1%2525255D%2525252FA%2525255B1%2525255D%252526linkType%25253D%252526checksum%25253D10%2526ie%253Dutf-8%2526f%253D3%2526tn%253Dbaidu%2526wd%253D51job%2526oq%253D%252525E6%25252599%252525BA%252525E8%25252581%25252594%252525E6%2525258B%2525259B%252525E8%25252581%25252598%2526rqlang%253Dcn%2526inputT%253D5846%2526bs%253D%2525E6%252599%2525BA%2525E8%252581%252594%2525E6%25258B%25259B%2525E8%252581%252598%26%7C%26adsnum%3D789233; 51job=cenglish%3D0%26%7C%26");
//        request.addHeader("Host","search.51job.com");
//        request.addHeader("Referer","http://www.51job.com/?from=baidupz");
     // request.addHeader("Upgrade-Insecure-Requests","SEARCH_ID=2d190aa8a3b043da920c9f420c8db60c; Version=1; Max-Age=86400; Expires=Mon, 27-Nov-2017 10:27:19 GMT; Path=/");
        request.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        HttpResponse response = null;
        try {
            httpClient.getParams().setParameter(
                CoreConnectionPNames. CONNECTION_TIMEOUT, 5000);
            // 读取超时
            httpClient.getParams().setParameter(
                    CoreConnectionPNames. SO_TIMEOUT, 5000);
            response = httpClient.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                String charset = getContentCharSet(entity);
                HttpEntity mEntity = response.getEntity();
                html = EntityUtils.toString(mEntity,charset);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return html.toString();
    }

    /**
     * 处理乱码
     * @param entity
     * @return
     * @throws Exception
     */
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
    /**
     * 获取网页html  post
     */
    public static String doHttpCilentPost(){
        return "";
    }


    public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout) throws Exception {
        String ctype = "application/json;charset=" + charset;
        byte[] content = {};
        if(params != null){
            content = params.getBytes(charset);
        }

        return doPost(url, ctype, content, connectTimeout, readTimeout);
    }
    public static String doPost(String url, String ctype, byte[] content,int connectTimeout,int readTimeout) throws Exception {
        HttpsURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try{
                SSLContext ctx = SSLContextClient.getSSLContext();
                conn = getConnection(new URL(url), METHOD_POST, ctype);
                conn.setHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            }catch(Exception e){
                //log.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
                throw e;
            }
            try{
                out = conn.getOutputStream();
                out.write(content);
                rsp = getResponseAsString(conn);
            }catch(IOException e){
               // log.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
                throw e;
            }

        }finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }


    private static HttpsURLConnection getConnection(URL url, String method, String ctype)
            throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "stargate");
        conn.setRequestProperty("Content-Type", ctype);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();

            char[] chars = new char[256];
            int count = 0;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            return writer.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = DEFAULT_CHARSET;

        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2) {
                        if (!StringUtils.isEmpty(pair[1])) {
                            charset = pair[1].trim();
                        }
                    }
                    break;
                }
            }
        }

        return charset;
    }
}
