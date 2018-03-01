package cn.edu.zzti.bibased.constant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * http 请求header头
 */
public class HttpHeaderConstant {

    private static  String user_trace_token = "20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce";
    private static  String user_trace_token2 ="20171204190931-9a32b27e-d8e3-11e7-829f-525400f775ce";
    private static  String user_trace_token3 = "20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce";
    private static String JSESSIONID = "ABAAABAAAGGABCB3A386C1E352571F93EF1CA0E3DC60C8E";
    private static String JSESSIONID2 = "ABAAABAAADEAAFI5D5E23A8C51C8A2CF493F15613955F28";
    private static String JSESSIONID3 = "ABAAABAAAGGABCB3A386C1E352571F93EF1CA0E3DC60C8E";
    /**
     * 拉钩get请求header
     */
    public static Map<String, Object> lagouGetHeader = new ConcurrentHashMap<>();

    static {
        lagouGetHeader.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        lagouGetHeader.put("Accept-Language", "zh-CN,zh;q=0.9");
        lagouGetHeader.put("Cache-Control", "max-age=0");
        lagouGetHeader.put("Connection", "keep-alive");
//        lagouGetHeader.put("Content-Length","23");
        lagouGetHeader.put("Content-Type", "applicationation/x-www-form-urlencoded; charset=UTF-8");
        lagouGetHeader.put("Cookie", "user_trace_token=" + user_trace_token + "; LGUID="+user_trace_token+"; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=" + JSESSIONID + "; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        lagouGetHeader.put("Host", "www.lagou.com");
        lagouGetHeader.put("Origin", "https://www.lagou.com");
        lagouGetHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        lagouGetHeader.put("X-Anit-Forge-Code", "0");
        lagouGetHeader.put("X-Anit-Forge-Token", "None");
        lagouGetHeader.put("X-Requested-With", "XMLHttpRequest");

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
    public static final Map<String,String>  jobHeader = new ConcurrentHashMap<>();
    static {

    }

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
