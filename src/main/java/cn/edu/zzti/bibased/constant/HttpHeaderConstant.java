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
    private static String JSESSIONID = "ABAAABAAAGGABCB3A386C1E352571F93EF1CA0E3DC60C8E";
    private static String JSESSIONID2 = "ABAAABAAADEAAFI5D5E23A8C51C8A2CF493F15613955F28";
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
        // lagouAjaxHeader.put("Content-Length",23);
        lagouAjaxHeader.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        //lagouAjaxHeader.put("Cookie","user_trace_token=20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce; LGUID=20171126164429-0464b234-d286-11e7-ab32-525400f775ce; JSESSIONID=ABAAABAACBHABBIF26E2EF92AF8C2FBA3772253D632BF83; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; _putrc=875B1A3E36073C27; login=true; unick=%E6%9D%A8%E6%99%BA%E7%BF%94; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=189; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.640132791.1512206234; _ga=GA1.2.1872966524.1511685872; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511685872,1511790697,1512206234; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512206975; LGSID=20171202171712-94e55ba3-d741-11e7-bbf7-525400f775ce; LGRID=20171202172934-4ea1f066-d743-11e7-bbf7-525400f775ce; SEARCH_ID=013a9d0ac89f4f51858fe10b937daec7; index_location_city=%E6%9D%AD%E5%B7%9E");
        //lagouAjaxHeader.put("Cookie","user_trace_token="+user_trace_token +"; LGUID=20171125141715-4817879d-d1a8-11e7-9a2d-5254005c3644; index_location_city=%E5%85%A8%E5%9B%BD; JSESSIONID="+JSESSIONID+"; PRE_UTM=; PRE_HOST=; PRE_SITE=; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F; TG-TRACK-CODE=index_navigation; _gat=1; _gid=GA1.2.124201744.1512039385; _ga=GA1.2.901608893.1511590633; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1511590640,1511590640,1511846105,1512039389; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1512039628; LGSID=20171130185625-1c137253-d5bd-11e7-b62b-525400f775ce; LGRID=20171130190027-ac9d17e2-d5bd-11e7-9b20-5254005c3644; SEARCH_ID=");
        lagouAjaxHeader.put("Cookie", "user_trace_token=20171126164429-0464ac5c-d286-11e7-ab32-525400f775ce; LGUID=20171126164429-0464b234-d286-11e7-ab32-525400f775ce; fromsite=\"localhost:8081\"; _ga=GA1.2.1872966524.1511685872; JSESSIONID=ABAAABAAAGGABCB3A386C1E352571F93EF1CA0E3DC60C8E; _gid=GA1.2.940900475.1518320483; index_location_city=%E5%85%A8%E5%9B%BD; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1517412525,1517730312,1518320482,1518342102; LGSID=20180211174140-c2f25b4f-0f0f-11e8-8443-525400f775ce; PRE_UTM=m_cf_cpt_baidu_pc; PRE_HOST=bzclk.baidu.com; PRE_SITE=http%3A%2F%2Fbzclk.baidu.com%2Fadrc.php%3Ft%3D06KL00c00f7Ghk60yUKm0FNkUsjrj3Kp00000PW4pNb000005ulNkW.THd_myIEIfK85yF9pywd0ZnquWKbuHPBPhfsnj01nAFWnfKd5HuDP1cYrHNAPDD1PYfsnRcsrRckfYf1wHTsn1m1nW6k0ADqI1YhUyPGujY1njn1nW0dn10YFMKzUvwGujYkP6K-5y9YIZK1rBtEILILQhk9uvqdQhPEUitOIgwVgLPEIgFWuHdVgvPhgvPsI7qBmy-bINqsmsKWThnqn1Tkn1T%26tpl%3Dtpl_10085_15730_11224%26l%3D1500117464%26attach%3Dlocation%253D%2526linkName%253D%2525E6%2525A0%252587%2525E9%2525A2%252598%2526linkText%253D%2525E3%252580%252590%2525E6%25258B%252589%2525E5%25258B%2525BE%2525E7%2525BD%252591%2525E3%252580%252591%2525E5%2525AE%252598%2525E7%2525BD%252591-%2525E4%2525B8%252593%2525E6%2525B3%2525A8%2525E4%2525BA%252592%2525E8%252581%252594%2525E7%2525BD%252591%2525E8%252581%25258C%2525E4%2525B8%25259A%2525E6%25259C%2525BA%2526xp%253Did%28%252522m6c247d9c%252522%29%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FH2%25255B1%25255D%25252FA%25255B1%25255D%2526linkType%253D%2526checksum%253D220%26ie%3Dutf-8%26f%3D8%26tn%3Dbaidu%26wd%3Dlagou%26rqlang%3Dcn%26inputT%3D1718; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F%3Futm_source%3Dm_cf_cpt_baidu_pc; TG-TRACK-CODE=index_navigation; _gat=1; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1518342504; LGRID=20180211174822-b2a7936e-0f10-11e8-afea-5254005c3644; SEARCH_ID=");
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

    public static void setJSESSIONID(String JSESSIONID) {
        HttpHeaderConstant.JSESSIONID = JSESSIONID;
    }

    public static void setUser_trace_token(String user_trace_token) {
        HttpHeaderConstant.user_trace_token = user_trace_token;
    }
}
