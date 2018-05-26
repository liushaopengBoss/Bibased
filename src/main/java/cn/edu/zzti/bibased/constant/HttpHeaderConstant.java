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
     *
    Cookie:ZP-ENV-FLAG=gray; adfbid=0; adfbid2=0; dywea=95841923.3154716493203137500.1527319253.1527319253.1527319253.1;
     dywec=95841923; dywez=95841923.1527319253.1.1.dywecsr=(direct)|dyweccn=(direct)|dywecmd=(none)|dywectr=undefined;
     __utma=269921210.1643125926.1527319253.1527319253.1527319253.1; __utmc=269921210;
     __utmz=269921210.1527319253.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none);
     Hm_lvt_38ba284938d5eddca645bb5e02a02006=1525744435,1526871224,1526887199,1527319253;
     __xsptplus30=30.1.1527319253.1527319253.1%234%7C%7C%7C%7C%7C%23%23Jy9r9t7B3KqV6ITeCK7zOiEQvsaLtZDt%23;
     _jzqa=1.3451372199187089000.1527319254.1527319254.1527319254.1; _jzqc=1; _jzqckmp=1; urlfrom=121126445;
     urlfrom2=121126445; adfcid=none; adfcid2=none; LastCity=%e9%83%91%e5%b7%9e; LastCity%5Fid=719; JSSearchModel=0;
     _qzjc=1; LastJobTag=%e4%ba%94%e9%99%a9%e4%b8%80%e9%87%91%7c%e8%8a%82%e6%97%a5%e7%a6%8f%e5%88%a9%7c%e5%b8%a6%e8%96%aa%e5%b9%b4%e5%81%87%7c%e7%bb%a9%e6%95%88%e5%a5%96%e9%87%91%7c%e5%91%98%e5%b7%a5%e6%97%85%e6%b8%b8%7c%e5%b9%b4%e5%ba%95%e5%8f%8c%e8%96%aa%7c%e5%85%a8%e5%8b%a4%e5%a5%96%7c%e9%a4%90%e8%a1%a5%7c%e5%8c%85%e4%bd%8f%7c%e5%91%a8%e6%9c%ab%e5%8f%8c%e4%bc%91%7c%e5%bc%b9%e6%80%a7%e5%b7%a5%e4%bd%9c%7c%e4%ba%a4%e9%80%9a%e8%a1%a5%e5%8a%a9%7c%e5%ae%9a%e6%9c%9f%e4%bd%93%e6%a3%80%7c%e5%8a%a0%e7%8f%ad%e8%a1%a5%e5%8a%a9%7c%e6%af%8f%e5%b9%b4%e5%a4%9a%e6%ac%a1%e8%b0%83%e8%96%aa%7c%e9%80%9a%e8%ae%af%e8%a1%a5%e8%b4%b4%7c%e5%85%8d%e8%b4%b9%e7%8f%ad%e8%bd%a6%7c%e5%8c%85%e5%90%83%7c%e8%a1%a5%e5%85%85%e5%8c%bb%e7%96%97%e4%bf%9d%e9%99%a9%7c%e5%b9%b4%e7%bb%88%e5%88%86%e7%ba%a2%7c%e6%88%bf%e8%a1%a5%7c%e5%88%9b%e4%b8%9a%e5%85%ac%e5%8f%b8%7c%e4%b8%8d%e5%8a%a0%e7%8f%ad%7c%e9%ab%98%e6%b8%a9%e8%a1%a5%e8%b4%b4%7c%e5%81%a5%e8%ba%ab%e4%bf%b1%e4%b9%90%e9%83%a8%7c%e8%82%a1%e7%a5%a8%e6%9c%9f%e6%9d%83%7c14%e8%96%aa%7c%e4%bd%8f%e6%88%bf%e8%a1%a5%e8%b4%b4%7c%e9%87%87%e6%9a%96%e8%a1%a5%e8%b4%b4%7c%e6%97%a0%e8%af%95%e7%94%a8%e6%9c%9f; LastSearchHistory=%7b%22Id%22%3a%22b803c30f-0f0c-411a-b320-71ede19e3fea%22%2c%22Name%22%3a%22%e9%83%91%e5%b7%9e+%2b+%e8%bd%af%e4%bb%b6%2f%e4%ba%92%e8%81%94%e7%bd%91%e5%bc%80%e5%8f%91%2f%e7%b3%bb%e7%bb%9f%e9%9b%86%e6%88%90+%2b+%e4%ba%92%e8%81%94%e7%bd%91%2f...%22%2c%22SearchUrl%22%3a%22http%3a%2f%2fsou.zhaopin.com%2fjobs%2fsearchresult.ashx%3fin%3d210500%3b160400%26jl%3d%25e9%2583%2591%25e5%25b7%259e%26isadv%3d0%26ispts%3d1%26isfilter%3d1%26bj%3d160000%26sj%3d2040%26sg%3db24c28d38f1c4633badb58525e031e49%26p%3d4%22%2c%22SaveTime%22%3a%22%5c%2fDate(1527320607775%2b0800)%5c%2f%22%7d; _qzja=1.1707429188.1526871682413.1526877287515.1527319282410.1527320597809.1527320609158.0.0.0.21.3;
     _qzjb=1.1527319282409.15.0.0.0; _qzjto=15.1.0; _jzqb=1.17.10.1527319254.1;
     Hm_lpvt_38ba284938d5eddca645bb5e02a02006=1527320720; dyweb=95841923.32.8.1527322033563; __utmb=269921210.32.9.1527322035399
     */

    public  static  Map<String,Object>  zhilianGetHeader = new LinkedHashMap<>();

    static {
        zhilianGetHeader.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        zhilianGetHeader.put(" Accept-Encoding","gzip, deflate");
        zhilianGetHeader.put("Accept-Language","zh-CN,zh;q=0.9");
        zhilianGetHeader.put("Connection","keep-alive");
        zhilianGetHeader.put("Cookie","");
        zhilianGetHeader.put("Host","sou.zhaopin.com");
        zhilianGetHeader.put("Referer","http://sou.zhaopin.com/jobs/searchresult.ashx?in=210500%3B160400&jl=%E5%8C%97%E4%BA%AC&isadv=0&ispts=1&isfilter=1&p=1&bj=160000&sj=044");
        zhilianGetHeader.put("Upgrade-Insecure-Requests",1);
        zhilianGetHeader.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
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
