package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.utils.MyHttpClientUtils;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpTests {

    
    public static void main(String[] args) {
        String url="https://www.lagou.com/jobs/positionAjax.json?jd=C%E8%BD%AE&px=default&city=%E4%B8%8A%E6%B5%B7&needAddtionalResult=false&isSchoolJob=0";
        String s = MyHttpClientUtils.doHttpCilentGet(url);
        Document parse = Jsoup.parse(s);
        Element head = parse.head();
        Element footer = parse.getElementById("footer");
        Element linkbox = parse.getElementById("linkbox");

    }
}
