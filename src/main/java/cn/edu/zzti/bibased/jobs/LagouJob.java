package cn.edu.zzti.bibased.jobs;

import cn.edu.zzti.bibased.service.http.HttpClientService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import cn.edu.zzti.bibased.utils.SpringContextUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 拉勾网的定时任务
 */
@Service
public class LagouJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(LagouJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        LagouService lagouService =(LagouService) applicationContext.getBean("lagouService");
        lagouService.getPositionDeailsInfomation();
        logger.info("lagou job start !! get lagou position detail!!");
    }

}
