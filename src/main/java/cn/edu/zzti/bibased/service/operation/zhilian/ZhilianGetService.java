package cn.edu.zzti.bibased.service.operation.zhilian;

import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.concurrent.CompletionService;

public class ZhilianGetService {

    @Resource
    private ThreadPoolTaskExecutor getInfoPool;
    /**
     * 注入无阻塞的
     */
    @Resource
    private CompletionService completionService;
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private EmailService emailService;
    @Resource
    private AcquisitionService acquisitionService;


    public void getPositionType(){
        String apiUrl = "http://sou.zhaopin.com/jobs/searchresult.ashx?bj=160000&sj=2040&in=210500%3B160400&jl=%E5%8C%97%E4%BA%AC&p=1&isadv=0";
        String s = httpClientService.doGet(apiUrl, null, null);

    }
}
