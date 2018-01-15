package cn.edu.zzti.bibased.utilsTests;

import cn.edu.zzti.bibased.BaseApplicationTests;
import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dao.lagou.LagouDao;
import cn.edu.zzti.bibased.dto.Position;
import cn.edu.zzti.bibased.service.handler.LagouHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.utils.IDUtils;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class HttpTests extends BaseApplicationTests {

    @Resource
    private HttpClientService httpClientService;
    @Resource
    private LagouDao lagouWriteDao;
    @Test
    public void getService() throws Exception {
        // String url = "http://search.51job.com/list/080200,000000,0000,00,9,99,Java%2B%25E5%25BC%2580%25E5%258F%2591,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=1&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        String url = "https://www.lagou.com";
        String html = httpClientService.doGet(url, null, HttpHeaderConstant.lagouGetHeader);

        for (int i = 0; i < 1000; i++) {
            List<Position> jobs = LagouHandler.getJobs(html);
            lagouWriteDao.batchInsertJobs(jobs);
            System.out.printf(""+i);
        }

    }


    @Test
    public void tt() throws Exception {
        long intId = IDUtils.getJobsIntId();
        System.out.printf(""+intId);
    }



}
