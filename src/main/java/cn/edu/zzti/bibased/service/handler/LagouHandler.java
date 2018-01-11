package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.Jobs;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
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
    public static List<Jobs> getJobs(String html){
        if(StringUtils.isNotEmpty(html)){
            Document parse = Jsoup.parse(html);
            List<Jobs> jobs =new LinkedList<>();
            Elements menuBox = parse.getElementsByClass("menu_box");
            int size = menuBox.size();
            for (int i = 0; i <size ; i++) {
                Element element = menuBox.get(i);
                String jobName = element.select("h2").text();
                Jobs job = new Jobs();
                jobs.add(job);
                job.setJobName(jobName);
//                job.setParentId(0);
                Elements categoryList = element.getElementsByClass("category-list");
                jobs(categoryList,jobs);
                Elements menuSubDn = element.getElementsByClass("menu_sub");
                jobs(menuSubDn,jobs);
            }
            return jobs;
        }
        return new LinkedList<>();
    }

    /**
     *解析获取数据
     *
     * @param elements
     * @param jobs
     */
    private static void jobs(Elements elements, List<Jobs> jobs){
        Elements aTagsList = elements.select("a");
        for (int j = 0; j <aTagsList.size() ; j++) {
            Element element1 = aTagsList.get(j);
            String jobUrl = element1.attr("href"); //职位的Url
            String jobsName = element1.text(); //职位名称
            Jobs job = new Jobs();
            jobs.add(job);
            job.setInclude(WebsiteEnum.LAGOU.getWebId());
            job.setJobName(jobsName);
            job.setJobUrl(jobUrl);
        }
    }
}
