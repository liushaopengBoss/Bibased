package cn.edu.zzti.bibased.constant;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaderConstant {


    public static final Map<String,String>  lagouHeader = new HashMap<>();
    public static final Map<String,String>  jobHeader = new HashMap<>();
    static {
        lagouHeader.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        lagouHeader.put("Accept-Language","zh-CN,zh;q=0.9");
        lagouHeader.put("Cache-Control","max-age=0");
        lagouHeader.put("Connection","keep-alive");
        lagouHeader.put("Content-Length","23");
        lagouHeader.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        lagouHeader.put("Cookie","user_trace_token=20171125141715-48178147-d1a8-11e7-9a2d-5254005c3644; LGUID=20171125141715-4817879d-d1a8-11e7-9a2d-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID=ABAAABAABEEAAJAC2DF2C9C20823C29B5348B276A969912; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=e7f6939decab442c9b13e734c14286e5");
        lagouHeader.put("Host","www.lagou.com");
        lagouHeader.put("Origin","https://www.lagou.com");
        lagouHeader.put("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        lagouHeader.put("X-Anit-Forge-Code","0");
        lagouHeader.put("X-Anit-Forge-Token","None");
        lagouHeader.put("X-Requested-With","XMLHttpRequest");

    }

}
