package cn.edu.zzti.bibased.jobs;

import cn.edu.zzti.bibased.service.operation.boss.BossGetService;
import cn.edu.zzti.bibased.service.operation.lagou.LagouGetService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

/**
 * 拉勾网的定时任务
 */
@Service
public class PositionDetailJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(PositionDetailJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        logger.info("lagou position job start !! get lagou position detail!!");
        LagouGetService lagouService =(LagouGetService) applicationContext.getBean("lagouGetService");
        lagouService.getPositionDeailsInfomation();
        logger.info("lagou job end !! get lagou position detail!!");

        logger.info("boss position job start !! get lagou position detail!!");
        BossGetService bossGetService = (BossGetService)applicationContext.getBean("bossGetService");
        bossGetService.getPositionDetails();
        logger.info("boss job end !! get lagou position detail!!");

    }

}
