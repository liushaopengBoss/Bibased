package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDesc;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.utils.DateUtils;
import cn.edu.zzti.bibased.utils.IDUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
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
            String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
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
                job.setDateVersion(dateVersion);
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
            job.setDateVersion(rootJob.getDateVersion());
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
                job2.setDateVersion(rootJob.getDateVersion());
            }

        }
    }

    /**
     * 获取全部招聘城市
     *
     * @param html
     * @return
     */
    public static List<City> getCitys(String html){
        if(StringUtils.isNotEmpty(html)){
            Document lagouCitys = Jsoup.parse(html);
            String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
            Elements hot = lagouCitys.getElementsByClass("workPosition");
            Elements aTags = hot.select("a");
            List<City> cityList = new LinkedList<>();
            for (int i = 0; i <aTags.size() ; i++) {
                String cityName = aTags.get(i).text().replace(">","");
                if(!"全部信息".equals(cityName) && !"全部城市".equals(cityName) && !"全国".equals(cityName) && !"不限".equals(cityName)){
                    City city = new City();
                    cityList.add(city);
                    city.setCityName(cityName);
                    city.setInclude(WebsiteEnum.LAGOU.getWebCode());
                    city.setDateVersion(dateVersion);
                }

            }
            return cityList;
        }
        return new LinkedList<>();
    }

    /**
     * 获取公司信息(html)
     *
     * @param html
     * @return
     */
    public static  List<Company>  getCompanys(String html,String  city){
        List<Company> companies = new LinkedList<>();
        if(StringUtils.isNotEmpty(html)){
            Document lagouCompanys = Jsoup.parse(html);
            Elements liTag = lagouCompanys.getElementById("company_list").getElementsByClass("item_con_list").select("li");
            for (int i = 0; i <liTag.size() ; i++) {
                Company company = new Company();
                companies.add(company);
                Elements pTags = liTag.get(i).getElementsByClass("top").select("p");
                String companyUrl = pTags.get(0).select("a").attr("href");
                String companyName = pTags.get(1).select("a").attr("title");
                String industry = pTags.get(2).text();
                String positionSlogan = pTags.get(3).text();
                Elements bottomATags = liTag.get(i).getElementsByClass("bottom").select("a");
                int positionNum =  Integer.parseInt(bottomATags.get(1).select("p").get(0).text());
                int resumeNate =  Integer.parseInt(bottomATags.get(2).select("p").get(0).text().replace("%",""));
                company.setCity(city);
                company.setCompanyName(companyName);
                company.setCompanyUrl(companyUrl);
                String [] strArr = industry.split("/");
                company.setFinanceStage(strArr[1]);
                company.setIndustryField(strArr[0]);
                company.setResumeRate(resumeNate);
                company.setPositionNum(positionNum);
                company.setPositionSlogan(positionSlogan);
                company.setIndustryField((WebsiteEnum.LAGOU.getWebCode()));
            }

        }
        return companies;
    }

    /**
     * 由公司页面中获取的城市
     *
     * @param html
     * @return
     */
    public static  List<City> getCityByCompany(String html){
        if(StringUtils.isNotEmpty(html)){
            Document lagouCompanys = Jsoup.parse(html);
            Elements cityWrapper = lagouCompanys.getElementsByClass("more-positions");
            Elements aTag = cityWrapper.select("a");
            List<City> cityList = new LinkedList<>();
            for (int i = 0; i <aTag.size() ; i++) {
                String id = aTag.get(i).attr("data-id");
                String cityName = aTag.get(i).text();
                City city = new City();
                cityList.add(city);
                city.setCityName(cityName);
                city.setLinkId(id);
            }
            return cityList;
        }
        return new LinkedList<>();
    }

    /**
     * 获取数据有多少页
     *
     * @param html
     * @return
     */
    public static int getTotalPageNum(String html){
        Document lagouCompanys = Jsoup.parse(html);
        Elements scripts = lagouCompanys.select("script");
        int pageNum = 0;
        for (int i = 0; i <scripts.size() ; i++) {

            if(scripts.get(i).html().contains("global.total")){
                String htmlInfo = scripts.get(i).html();
                String globalTotal = htmlInfo.substring(htmlInfo.indexOf("global.total"), htmlInfo.indexOf("global.total") + 25);
                int totalNum = Integer.parseInt(globalTotal.substring(globalTotal.indexOf("\"")+1, globalTotal.lastIndexOf("\"")));
                String globalps = htmlInfo.substring(htmlInfo.indexOf("global.ps"), htmlInfo.indexOf("global.ps") + 20);
                int ps = Integer.parseInt(globalps.substring(globalps.indexOf("\"")+1, globalps.lastIndexOf("\"")));
                pageNum = totalNum/ps;
               break;
            }
        }
        return  pageNum;
    }


    public static PositionDesc  getPositionDesc(String html){
        PositionDesc desc = new PositionDesc();
        Document lagouPositionDesc = Jsoup.parse(html);
        Elements jobBtElement = lagouPositionDesc.getElementsByClass("job_bt");
        String text = jobBtElement.get(0).text();
        if(text != null){
            int index1 = text.indexOf("岗位职责");
            int index2 = text.indexOf("任职要求");
            String substring = text.substring(index1+5, index2);
            String substring1 = text.substring(index2+5);
            desc.setPostDuties(substring);
            desc.setTenureRequirements(substring1);
        }
        return desc;
    }

}
