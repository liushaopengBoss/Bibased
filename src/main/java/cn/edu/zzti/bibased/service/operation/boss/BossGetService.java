package cn.edu.zzti.bibased.service.operation.boss;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.BossHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouQueryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BossGetService {

    @Resource
    private LagouQueryService lagouQueryService;
    @Resource
    private HttpClientService httpClientService;
    @Resource
    private EmailService emailService;
    @Resource
    private AcquisitionService acquisitionService;

    public void getBossPositionType(){
        String apiUrl = "https://www.zhipin.com/";
        String html = httpClientService.doGet(apiUrl, null, HttpHeaderConstant.bossGetHeader);
        List<Positions> jobs = BossHandler.getJobs(html);
        acquisitionService.batchAddJob(jobs);
    }
}
