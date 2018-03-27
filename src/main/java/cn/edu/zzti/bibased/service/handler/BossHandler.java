package cn.edu.zzti.bibased.service.handler;

import cn.edu.zzti.bibased.constant.WebsiteEnum;
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
 * 处理BOSS直聘的网页信息
 * <p>
 * Created by huaidou on  2018/3/27
 */
public class BossHandler {

    /**
     * 从BOSS直聘获取所有的职位类别信息
     *
     * @param html
     * @return
     */
    public static List<Positions> getJobs(String html){
        if(StringUtils.isNotEmpty(html)){
            Document bossJobs = Jsoup.parse(html);
            List<Positions> jobs =new LinkedList<>();
            String dateVersion = DateUtils.formatStr(new Date(), DateUtils.YYMMDDHHmmssSSS);
            Elements jobMenu = bossJobs.getElementsByClass("job-menu");
            for (int i = 0; i <jobMenu.size(); i++) {
                Elements menuSubs = jobMenu.get(i).getElementsByClass("menu-sub");
                Elements h4 = jobMenu.get(i).getElementsByTag("h4");
                for (int j = 0; j <menuSubs.size() ; j++) {
                    String rootName = h4.get(j).text();
                    long jobsIntId = IDUtils.getJobsIntId();
                    Positions rootPositon = new Positions();
                    rootPositon.setInclude(WebsiteEnum.BOSS.getWebCode());
                    rootPositon.setPositionName(rootName);
                    rootPositon.setLeaf(Boolean.FALSE);
                    rootPositon.setDateVersion(dateVersion);
                    rootPositon.setParentId(0);
                    rootPositon.setRootId(jobsIntId);
                    jobs.add(rootPositon);
                    Elements text = menuSubs.get(j).getElementsByClass("text");
                    for (int k = 0; k <text.size() ; k++) {
                        Elements a = text.get(k).getElementsByTag("a");
                        for (int l = 0; l <a.size() ; l++) {
                            Positions leafPositon = new Positions();
                            leafPositon.setInclude(WebsiteEnum.BOSS.getWebCode());
                            leafPositon.setPositionName(a.get(l).text());
                            leafPositon.setDateVersion(dateVersion);
                            leafPositon.setParentId(jobsIntId);
                            leafPositon.setRootId(jobsIntId);
                            leafPositon.setPositionUrl("https://www.zhipin.com/"+a.get(l).attr("href"));
                            jobs.add(leafPositon);
                        }

                    }
                }
            }
            return jobs;
        }
        return new LinkedList<>();
    }

}
