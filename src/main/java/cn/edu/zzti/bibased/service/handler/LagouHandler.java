package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

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
    public static List<Positions> getJobs(String html){
        if(StringUtils.isNotEmpty(html)){
            Document lagouJobs = Jsoup.parse(html);
            List<Positions> jobs =new LinkedList<>();
            Elements menuBox = lagouJobs.getElementsByClass("menu_box");
            int size = menuBox.size();
            for (int i = 0; i <size ; i++) {
                Element element = menuBox.get(i);
                String jobName = element.select("h2").text();
                Positions job = new Positions();//root
                jobs.add(job);
                job.setPositionName(jobName);
                job.setInclude(WebsiteEnum.LAGOU.getWebCode());
                long jobsIntId = IDUtils.getJobsIntId();
                job.setId(jobsIntId);
                job.setRootId(jobsIntId);
                job.setLeaf(false);
                Elements menuSubDn = element.getElementsByClass("menu_sub");
                jobs(menuSubDn,jobs,job);
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
    private static void jobs(Elements elements, List<Positions> jobs, Positions rootJob){
        Elements titles = elements.select("dt");
        Elements contents = elements.select("dd");
        for (int i = 0; i <titles.size() ; i++) {
            Element titleElement = titles.get(i);
            String title = titleElement.select("span").text();
            Positions job = new Positions();
            jobs.add(job);
            job.setInclude(WebsiteEnum.LAGOU.getWebCode());
            job.setPositionName(title);
            job.setId(IDUtils.getJobsIntId());
            job.setParentId(rootJob.getId());
            job.setRootId(rootJob.getRootId());
            job.setLeaf(false);
            Elements aTages = contents.get(i).select("a");
            for (int j = 0; j <aTages.size() ; j++) {
                Element element1 = aTages.get(j);
                String jobUrl = element1.attr("href"); //职位的Url
                String jobsName = element1.text(); //职位名称
                Positions job2 = new Positions();
                jobs.add(job2);
                job2.setInclude(WebsiteEnum.LAGOU.getWebCode());
                job2.setPositionName(jobsName);
                job2.setPositionUrl(jobUrl);
                job2.setParentId(job.getId());
                job2.setRootId(rootJob.getRootId());
            }

        }
    }

    public static List<City> getCitys(String html){
        if(StringUtils.isNotEmpty(html)){
            Document lagouCitys = Jsoup.parse(html);

            Elements hot = lagouCitys.getElementsByClass("workPosition");
            Elements aTags = hot.select("a");
            List<City> cityList = new LinkedList<>();
            for (int i = 0; i <aTags.size() ; i++) {
                String cityName = aTags.get(i).text().replace(">","");
                if(!"全部信息".equals(cityName)){
                    City city = new City();
                    cityList.add(city);
                    city.setCityName(cityName);
                    city.setInclude(WebsiteEnum.LAGOU.getWebCode());
                }

            }
            return cityList;
        }
        return new LinkedList<>();
    }
}
