package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import jdk.nashorn.internal.objects.NativeArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpTests extends BaseApplicationTests {

    @Autowired
    private HttpClientService httpClientService;

    @Test
    public void getService() throws Exception {

        // String url = "http://search.51job.com/list/080200,000000,0000,00,9,99,Java%2B%25E5%25BC%2580%25E5%258F%2591,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=1&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        String url = "https://www.lagou.com";
        String s = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        Document parse = Jsoup.parse(s);
        Element head = parse.head();
        Map<String,String> jobsParam= new LinkedHashMap<>();
        Elements menuBox = parse.getElementsByClass("menu_box");
        int size = menuBox.size();  //menu_box
        for (int i = 0; i <size ; i++) {
            Element element = menuBox.get(i);
            Elements categoryList = element.getElementsByClass("category-list");
            jobs(categoryList,jobsParam);
            Elements menuSubDn = element.getElementsByClass("menu_sub");
            jobs(menuSubDn,jobsParam);
        }
        System.out.printf("");
    }

    private void jobs(Elements elements,Map<String,String> jobsParam){
        Elements aTagsList = elements.select("a");
        for (int j = 0; j <aTagsList.size() ; j++) {
            Element element1 = aTagsList.get(j);
            String href = element1.attr("href");
            String jobsName = element1.text();
            jobsParam.put(jobsName,href);
        }
    }
}
