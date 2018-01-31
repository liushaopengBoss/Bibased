package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import cn.edu.zzti.bibased.utils.IDUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class HttpTests extends BaseApplicationTests {

    @Resource
    private HttpClientService httpClientService;
    @Resource
    private LagouDao lagouWriteDao;
    @Resource
    LagouService lagouService;
    @Test
    public void getService4544() throws Exception {
        // String url = "http://search.51job.com/list/080200,000000,0000,00,9,99,Java%2B%25E5%25BC%2580%25E5%258F%2591,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=1&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        String url = "https://www.lagou.com";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);

        for (int i = 0; i < 1000; i++) {
            List<Positions> jobs = LagouHandler.getJobs(html);
           // lagouWriteDao.batchInsertJobs(jobs);
            System.out.printf(""+url+"\n");
            System.out.printf(""+i);
        }
        System.out.printf(""+url);
    }


    @Test
    public void tt23543() throws Exception {
        for (int i = 0; i < 1000; i++) {
            long intId = IDUtils.getJobsIntId();
            System.out.printf(""+intId);
        }

    }

    @Test
    public void tt23() throws Exception {
        String url = "https://www.lagou.com/gongsi/";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<City> cityByCompany = LagouHandler.getCityByCompany(html);
    }
    @Test
    public void tt423() throws Exception {
        String url = "https://www.lagou.com/gongsi/";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        List<Company> companys = LagouHandler.getCompanys(html, "");
    }

    @Test
    public void tt563() throws Exception {
        String url = "https://www.lagou.com/gongsi/184-0-0";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        int totalPageNum = LagouHandler.getTotalPageNum(html);
        System.out.printf(""+totalPageNum);
//        /** HtmlUnit请求web页面 */
//        WebClient wc = new WebClient(BrowserVersion.CHROME);
//        wc.getOptions().setUseInsecureSSL(true);
//        wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
//        wc.getOptions().setCssEnabled(true); // 禁用css支持
//        wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
//        wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
//        wc.getOptions().setDoNotTrackEnabled(false);
//        HtmlPage page = wc
//                .getPage(url);
//        String s = page.asText();
//        Document lagouCompanys = Jsoup.parse(s);
//        Elements item_con_pager = lagouCompanys.getElementsByClass("item_con_pager");
//        String text = item_con_pager.text();
//        System.out.printf(""+text);
//        Elements item_con_pager = lagouCompanys.getElementsByClass("item_con_pager");
    }
    @Test
    public void textCompany(){
        lagouService.collectionCompanyInfomation();
    }
}
