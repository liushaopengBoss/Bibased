package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.utils.MyHttpClientUtils;
import org.apache.http.client.utils.HttpClientUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HttpTests {

    
    public static void main(String[] args) {
    
       // String url = "http://search.51job.com/list/080200,000000,0000,00,9,99,Java%2B%25E5%25BC%2580%25E5%258F%2591,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=1&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        String url = "https://www.lagou.com/zhaopin/Java/?labelWords=label";
        String s = MyHttpClientUtils.doHttpCilentGet(url);
        Document parse = Jsoup.parse(s);
        Element head = parse.head();
        Element footer = parse.getElementById("s_position_list");
        Element linkbox = parse.getElementById("linkbox");

    }
}