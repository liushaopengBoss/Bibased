package cn.edu.zzti.bibased.constant;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * http 请求header头
 */
public class HttpHeaderConstant {

    private static  String user_trace_token = "20171204190931-9a32b27e-d8e3-11e7-829f-525400f775ce";
    private static  String user_trace_token2 ="20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce";
    private static  String user_trace_token3 = "20171204190931-9a32b27e-d8e3-11e7-829f-525400f775ce";
    private static String JSESSIONID = "ABAAABAAAIAACBI194E7FFC32E96282D13A026C00B1B198";
    private static String JSESSIONID2 = "ABAAABAAADEAAFI5D5E23A8C51C8A2CF493F15613955F28";
    private static String JSESSIONID3 = "ABAAABAAAIAACBI194E7FFC32E96282D13A026C00B1B198";
    /**
     * 拉钩get请求header
     */
    public static Map<String, Object> lagouGetHeader = new LinkedHashMap<>();

    static {
        lagouGetHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        lagouGetHeader.put("Accept-Encoding", "gzip, deflate, br");
        lagouGetHeader.put("Accept-Language", "zh-CN,zh;q=0.9");
        lagouGetHeader.put("Cache-Control", "max-age=0");
        lagouGetHeader.put("Connection", "keep-alive");
//        lagouGetHeader.put("Content-Type", "applicationation/x-www-form-urlencoded; charset=UTF-8");
       // lagouGetHeader.put("Cookie", "user_trace_token=" + user_trace_token + "; LGUID="+user_trace_token+"; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=" + JSESSIONID + "; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        //lagouGetHeader.put("Cookie","user_trace_token=20171204190931-9a32b27e-d8e3-11e7-829f-525400f775ce; LGUID=20171204190931-9a32b4e9-d8e3-11e7-829f-525400f775ce; _ga=GA1.2.1002046000.1512385771; JSESSIONID=ABAAABAAADEAAFI73B5C188BF0F77CDD891D6BCC1ACE8B0; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1521543898,1522124283,1522147458,1522645727; _gat=1; LGSID=20180402130847-ecaabc03-3633-11e8-b6d5-5254005c3644; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; index_location_city=%E5%85%A8%E5%9B%BD; _gid=GA1.2.426115179.1522645731; TG-TRACK-CODE=index_navigation;  LGRID=20180402130914-fca506a9-3633-11e8-b6d5-5254005c3644;SEARCH_ID=");
        lagouGetHeader.put("Cookie","user_trace_token="+JSESSIONID+"; LGUID=20171126164429-0464b234-d286-11e7-ab32-525400f775ce; fromsite=\"localhost:8081\"; _ga=GA1.2.1872966524.1511685872; WEBTJ-ID=20180506074424-16332b134fb151-0943d69e419312-3b60450b-1049088-16332b134fc3ef; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1525563865; _gid=GA1.2.1470732280.1525563865; JSESSIONID=ABAAABAAADEAAFIBE5EDBEAA3948645A993265AD7A19E15; index_location_city=%E5%85%A8%E5%9B%BD; TG-TRACK-CODE=index_navigation; SEARCH_ID=3d436d73ed4945c1a08bf72c865c3c6a; LGSID=20180506101216-e5c9795c-50d2-11e8-87f2-525400f775ce; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Fwww.lagou.com%2Fzhaopin%2FJava%2F%3FlabelWords%3Dlabel; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2F4477797.html; LGRID=20180506101216-e5c97b97-50d2-11e8-87f2-525400f775ce; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1525572740");
        lagouGetHeader.put("Host", "www.lagou.com");
        lagouGetHeader.put("Referer", "https://www.lagou.com/zhaopin/Java/?labelWords=label");
       // lagouGetHeader.put("Upgrade-Insecure-Requests", 1);
        lagouGetHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
    }
    //https://www.lagou.com/upload/ltm/oss.html?u=/gongsi/213-0-0&q=171&n=172&d=105&l=261&dns=0&p=545&pi=43&qn=387&t=1519818522275
    //https://www.lagou.com/upload/ltm/oss.html?u=/gongsi/252-0-0&q=304&n=310&d=105&l=340&dns=0&p=768&pi=33&qn=613&t=1519818623401

    /**
     * lagou ajax请求Header(post请求)
     */
    public static Map<String, Object> lagouAjaxHeader = new ConcurrentSkipListMap<>();

    static {
        /**
         * Referer:https://www.lagou.com/jobs/list_Java?px=default&city=%E5%8C%97%E4%BA%AC
         * Content-Type:application/x-www-form-urlencoded; charset=UTF-8
         * Accept-Encoding
         */
        lagouAjaxHeader.put("Accept", "application/json, text/javascript, */*; q=0.01");
        lagouAjaxHeader.put("Accept-Encoding", "gzip, deflate, br");
        lagouAjaxHeader.put("Accept-Language", "zh-CN,zh;q=0.9");
        lagouAjaxHeader.put("Connection", "keep-alive");
        lagouAjaxHeader.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        lagouAjaxHeader.put("Cookie","user_trace_token="+user_trace_token +"; LGUID="+user_trace_token +"; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID="+JSESSIONID+"; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        lagouAjaxHeader.put("Host", "www.lagou.com");
        lagouAjaxHeader.put("Origin", "https://www.lagou.com");
        lagouAjaxHeader.put("Referer", "https://www.lagou.com");
        lagouAjaxHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        lagouAjaxHeader.put("X-Anit-Forge-Code", 0);
        lagouAjaxHeader.put("X-Anit-Forge-Token", "None");
        lagouAjaxHeader.put("X-Requested-With", "XMLHttpRequest");
    }


    public static Map<String, Object> compaanyParam = new LinkedHashMap<>();

    static {
        compaanyParam.put("first",false);
        compaanyParam.put("pn",1);
        compaanyParam.put("sortField",0);
        compaanyParam.put("havemark",0);
}
    public static final Map<String,Object>  bossGetHeader = new ConcurrentHashMap<>();
    static {
        bossGetHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        bossGetHeader.put("Accept-Encoding", "gzip, deflate, br");
        bossGetHeader.put("Accept-Language", "zh-CN,zh;q=0.9");
        bossGetHeader.put("Cache-Control", "max-age=0");
        bossGetHeader.put("Cookie:", "JSESSIONID=\"\"; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1521518076,1522150597; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1522150597; __c=1522150597; __g=-; __l=l=%2Fwww.zhipin.com%2F&r=; __a=58605553.1517829617.1521518076.1522150597.20.3.1.20");
        bossGetHeader.put("Host", "www.zhipin.com");
        bossGetHeader.put("Origin", "https://www.lagou.com");
        bossGetHeader.put("Upgrade-Insecure-Requests", 1);
        bossGetHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
    }
    public  static  Map<String,Object>  bossPositionDetailGetHeader = new HashMap<>();

    static {
        bossPositionDetailGetHeader.put(":authority","www.zhipin.com");
        bossPositionDetailGetHeader.put(":method","GET");
        bossPositionDetailGetHeader.put(":path","");
        bossPositionDetailGetHeader.put(":scheme","https");
        bossPositionDetailGetHeader.put("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        bossPositionDetailGetHeader.put("accept-encoding","gzip, deflate, br");
        bossPositionDetailGetHeader.put("accept-language","zh-CN,zh;q=0.9");
        bossPositionDetailGetHeader.put("cookie","lastCity=101210100; JSESSIONID=\"\";" +
                " Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1521947993,1522511497,1522570866,1522849305;" +
                " __c=1522849305; __g=-; __l=l=%2Fwww.zhipin.com%2F&r=; " +
                "toUrl=https%3A%2F%2Fwww.zhipin.com%2Fjob_detail%2F; " +
                "t=2hC6RJArNhx9pdCs; wt=2hC6RJArNhx9pdCs; " +
                "Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1523014640;" +
                " __a=60528412.1515908295.1522570866.1522849305.89.5.53.89");
        bossPositionDetailGetHeader.put("referer","https://www.zhipin.com/c101280600-p100103/?ka=sel-city-101280600");
        bossPositionDetailGetHeader.put("upgrade-insecure-requests",1);
        bossPositionDetailGetHeader.put("user-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
    }

    /**
     * Cache-Control: max-age=0
     Connection: keep-alive
     Cookie: lastCity=101210100; JSESSIONID=""; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1521518076,1522150597; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1522150597; __c=1522150597; __g=-; __l=l=%2Fwww.zhipin.com%2F&r=; __a=58605553.1517829617.1521518076.1522150597.20.3.1.20
     Host: www.zhipin.com
     Upgrade-Insecure-Requests: 1
     User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36
     */

    public static void setStatus() {
        if(HttpHeaderConstant.JSESSIONID == HttpHeaderConstant.JSESSIONID2){
            HttpHeaderConstant.JSESSIONID = HttpHeaderConstant.JSESSIONID3;
        }else{
            HttpHeaderConstant.JSESSIONID =HttpHeaderConstant.JSESSIONID2;
        }
        if(HttpHeaderConstant.user_trace_token == HttpHeaderConstant.user_trace_token2){
            HttpHeaderConstant.user_trace_token = HttpHeaderConstant.user_trace_token3;
        }else{
            HttpHeaderConstant.user_trace_token =HttpHeaderConstant.user_trace_token3;
        }

    }

    public static void setUser_trace_token(String user_trace_token) {
        HttpHeaderConstant.user_trace_token = user_trace_token;
    }
}
