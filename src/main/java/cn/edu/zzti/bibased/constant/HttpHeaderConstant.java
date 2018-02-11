package cn.edu.zzti.bibased.constant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * http 请求header头
 */
public class HttpHeaderConstant {

    private static final String user_trace_token = "20171204190931-9a32b27e-d8e3-11e7-829f-525400f775ce";
    private static String JSESSIONID = "ABAAABAAAGGABCB6B6E54153AAD9C8C279E0573C23DF61F";

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
        lagouGetHeader.put("Cookie", "user_trace_token=" + user_trace_token + "; LGUID=20171125141715-4817879d-d1a8-11e7-9a2d-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=" + JSESSIONID + "; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        lagouGetHeader.put("Host", "www.lagou.com");
        lagouGetHeader.put("Origin", "https://www.lagou.com");
        lagouGetHeader.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        lagouGetHeader.put("X-Anit-Forge-Code", "0");
        lagouGetHeader.put("X-Anit-Forge-Token", "None");
        lagouGetHeader.put("X-Requested-With", "XMLHttpRequest");

    }

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
        // lagouAjaxHeader.put("Content-Length",23);
        lagouAjaxHeader.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //lagouAjaxHeader.put("Cookie","user_trace_token=20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce; LGUID=20171126164429-0464b234-d286-11e7-ab32-525400f775ce; JSESSIONID=ABAAABAACBHABBIF26E2EF92AF8C2FBA3772253D632BF83; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; _putrc=875B1A3E36073C27; login=true; unick=%E6%9D%A8%E6%99%BA%E7%BF%94; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=189; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.640132791.1512206234; _ga=GA1.2.1872966524.1511685872; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511685872,1511790697,1512206234; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512206975; LGSID=20171202171712-94e55ba3-d741-11e7-bbf7-525400f775ce; LGRID=20171202172934-4ea1f066-d743-11e7-bbf7-525400f775ce; SEARCH_ID=013a9d0ac89f4f51858fe10b937daec7; index_location_city=%E6%9D%AD%E5%B7%9E");
        lagouAjaxHeader.put("Cookie","user_trace_token="+user_trace_token +"; LGUID=20171125141715-4817879d-d1a8-11e7-9a2d-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID="+JSESSIONID+"; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        //lagouAjaxHeader.put("Cookie", "user_trace_token=20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce; LGUID=20171126164429-0464b234-d286-11e7-ab32-525400f775ce; fromsite=\"localhost:8081\"; _ga=GA1.2.1872966524.1511685872; index_location_city=%E5%8C%97%E4%BA%AC; JSESSIONID=ABAAABAAAGGABCB6B6E54153AAD9C8C279E0573C23DF61F; _gid=GA1.2.501554749.1517730303; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1516533433,1517122069,1517412525,1517730312; TG-TRACK-CODE=index_navigation; LGSID=20180204181009-948d72ea-0993-11e8-aa06-525400f775ce; PRE_UTM=; PRE_HOST=; PRE_SITE=https%3A%2F%2Fwww.lagou.com%2Fzhaopin%2FJava%2F%3FlabelWords%3Dlabel; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2Fjobs%2F4087854.html; SEARCH_ID=428a794879134bc6910a4df4eb69031e; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517740493; LGRID=20180204183453-09432a2c-0997-11e8-aa0d-525400f775ce");
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
}
