package cn.edu.zzti.bibased.service.operation.zhilian;

import cn.edu.zzti.bibased.constant.HttpHeaderConstant;
import cn.edu.zzti.bibased.dto.PositionDetail;
import cn.edu.zzti.bibased.dto.Positions;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.handler.ZhilianHandler;
import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.base.AcquisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletionService;

@Service
public class ZhilianGetService {
    Logger logger = LoggerFactory.getLogger(ZhilianGetService.class);
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


    @Async
    public void getPositionType(){
        String apiUrl = "http://sou.zhaopin.com/jobs/searchresult.ashx?in=210500%3B160400&jl=%E5%8C%97%E4%BA%AC&isadv=0&ispts=1&isfilter=1&p=1&bj=160000&sj=2034";
        String html = httpClientService.doGet(apiUrl, null, null);
        logger.info(html);
        List<Positions> positions = ZhilianHandler.handlerPositionType(html);
        acquisitionService.batchAddJob(positions);

    }
    @Async
    public void  getPosiotnDetails(){
        String apiUrl = "http://sou.zhaopin.com/jobs/searchresult.ashx?in=210500%3B160400&jl=%E5%8C%97%E4%BA%AC&isadv=0&ispts=1&isfilter=1&p=1&bj=160000&sj=2034";
        String html = httpClientService.doGet(apiUrl, null, null);
        logger.info(html);
        List<PositionDetail> positionDetails = ZhilianHandler.handlePositionDetail(html);
        acquisitionService.batchAddPositionDetails(positionDetails);
    }
}
