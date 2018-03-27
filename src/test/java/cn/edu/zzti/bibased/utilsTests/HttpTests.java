package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.City;
import cn.edu.zzti.bibased.dto.Company;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.execute.BaseExecuter;
import cn.edu.zzti.bibased.execute.PositionDetailExecute;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.BossHandler;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouOperationService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import cn.edu.zzti.bibased.utils.DateUtils;
import cn.edu.zzti.bibased.utils.IDUtils;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.*;

public class HttpTests extends BaseApplicationTests {
    private Logger logger = LoggerFactory.getLogger(HttpTests.class);
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private LagouOperationService lagouOperationService;
    @Resource
    private LagouDao lagouDao;
    @Resource
    LagouService lagouService;
    @Resource
    private EmailService emailService;
    @Test
    public void getService() throws Exception {
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
    public void idTests() throws Exception {
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
        String url = "https://www.lagou.com/gongsi";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);
        int totalPageNum = LagouHandler.getTotalPageNum(html);
        System.out.printf(""+totalPageNum);

    }
    @Test
    public void textCompany(){
        String apiUrl = "https://www.zhilian.com/";
        String html = httpClientService.doGet(apiUrl, null, null);
//        lagouService.collectionCompanyInfomation();
        try {
            Thread.sleep(300000);
        }catch (Exception e){}
    }

    @Test
    public void webClientTest() throws  Exception{
        try {
            String url = "https://www.2cto.com/kf/201706/648694.html";
            WebClient wc = new WebClient(BrowserVersion.CHROME);
            wc.getOptions().setUseInsecureSSL(true);
            wc.getOptions().setJavaScriptEnabled(true); // 启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(true); // 禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(false); // js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(100000); // 设置连接超时时间 ，这里是10S。如果为0，则无限期等待
            wc.getOptions().setDoNotTrackEnabled(false);
            HtmlPage page = wc.getPage(url);
            String s = page.asText();
            System.out.printf("" + s);
        }catch (Exception e){}
    }
    @Test
    public void CookieTest(){
        String url = "https://www.lagou.com/gongsi/";
        String html = httpClientService.doGet(url, null, null);
    }
    @Test
    public void positiionTests(){
        lagouService.getPositionDeailsInfomation();
    }

    @Test
    public void  positionDetailsTest(){
        lagouService.getCompanyInfomationV2();
        String apiUrl = "https://www.lagou.com/jobs/companyAjax.json?px=default&city="+"北京"+"&needAddtionalResult=false&isSchoolJob=0";

        Map<String,Object> param = new LinkedHashMap<>();
        param.put("first",false);
        param.put("pn",1);
        param.put("kd","Java");
        Map<String, Object> lagouAjaxHeader = HttpHeaderConstant.lagouAjaxHeader;

        lagouAjaxHeader.put("Referer",apiUrl);
        String cookie = lagouAjaxHeader.get("Cookie").toString()+ UUID.randomUUID().toString().replace("-","").toString()+";";
        lagouAjaxHeader.put("Cookie",cookie);
        BaseExecuter positonDetailTask = new PositionDetailExecute();
        positonDetailTask.setParams(param);
        positonDetailTask.setHeaders(lagouAjaxHeader);
        positonDetailTask.setApiUrl(apiUrl);


    }

    @Test
    public void dFireTests(){

         Map<String, Object> compaanyParam = new LinkedHashMap<>();
        compaanyParam.put("app_version","5.6.48");
        compaanyParam.put("device_id","3702341F583243739068247228F37D65");
        compaanyParam.put("result_id","9993447161bc9a940161bc9deca20001");
        compaanyParam.put("session_key","10000899934471938975bc0a7b40c98794ae1e66919abe");
        compaanyParam.put("sign","0cc44440fd625ab28de578ba5ede004f");
        compaanyParam.put("timestamp","1519288972.98673");
        compaanyParam.put("version_code","5064800");
        String get_history_result = "http://10.1.24.205:8080/boss-api/health_check/v2/get_history_result?app_key=200041&s_os=ios&s_sc=375x667&s_apv=5.6.48&s_uid=48f36dc57881409c854ac6170b44d43b&s_net=6&ip=223.93.161.98&format=json&ttm=901e1952207ff2058e711d23047b2487&s_osv=10.3.3&s_br=iphone&s_eid=99934471&s_did=118df65d929d490b9274c011a94996a3";
        String json = httpClientService.doGet(get_history_result, compaanyParam, compaanyParam);
    }
    @Test
    public void dateTets(){
        logger.error(DateUtils.formatStr(new Date(),DateUtils.YYMMDDHHmmssSSS));
    }

    @Test
    public void subString(){
        Map<String, Object> header = HttpHeaderConstant.lagouAjaxHeader;

        logger.info(header.get("Cookie").toString().substring(0,header.get("Cookie").toString().indexOf("SEARCH_ID=")+10));
    }

    @Test
    public void sendMail(){
        emailService.sendSimpleMail("采集系统告警！","请尽快处理！");
    }


    @Test
    public void lastCreatTime(){
        Long aLong = lagouOperationService.queryLastPositionCreateTime("北京","Java");
        logger.info(aLong+"");
    }

    @Test
    public void positionWorkYearTest(){
        List<PositionDetail> positionDetails = lagouOperationService.queryWorkYearNums();
    }
    @Resource
    JavaMailSenderImpl mailSender;
    @Test
    public void senfTets(){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("1396647019@qq.com");//收件人邮箱地址
        mail.setFrom("biggress@163.com");//收件人
        mail.setSubject("spring自带javamail发送的邮件");//主题
        mail.setText("hello this mail is from spring javaMail ");//正文
        mailSender.send(mail);
    }

    @Test
    public void BossPositionTest(){
        String apiUrl = "https://www.zhipin.com/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.bossGetHeader);
        BossHandler.getJobs(html);
    }
}
