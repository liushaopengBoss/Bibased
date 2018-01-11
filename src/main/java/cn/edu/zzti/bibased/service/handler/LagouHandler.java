package cn.edu.zzti.bibased.service.handler;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 处理拉勾网的网页信息
 * <p>
 * Created by huaidou on  2018/1/11
 */
public class LagouHandler {
    /**
     * 从拉钩网获取所有的职位信息
     *
     * @param html
     * @return
     */
    public Map<String,String> getJobs(String html){
        if(StringUtils.isNotEmpty(html)){
            Document parse = Jsoup.parse(html);
            Element head = parse.head();
            Map<String,String> jobsMap= new LinkedHashMap<>();
            Elements menuBox = parse.getElementsByClass("menu_box");
            int size = menuBox.size();
            for (int i = 0; i <size ; i++) {
                Element element = menuBox.get(i);
                Elements categoryList = element.getElementsByClass("category-list");
                jobs(categoryList,jobsMap);
                Elements menuSubDn = element.getElementsByClass("menu_sub");
                jobs(menuSubDn,jobsMap);
            }
            return jobsMap;
        }
        return new LinkedHashMap<>(0);
    }

    /**
     *解析获取数据
     *
     * @param elements
     * @param jobsParam
     */
    private void jobs(Elements elements, Map<String,String> jobsParam){
        Elements aTagsList = elements.select("a");
        for (int j = 0; j <aTagsList.size() ; j++) {
            Element element1 = aTagsList.get(j);
            String href = element1.attr("href"); //职位的Url
            String jobsName = element1.text(); //职位名称
            jobsParam.put(jobsName,href);
        }
    }
}
