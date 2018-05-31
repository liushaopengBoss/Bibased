package cn.edu.zzti.bibased.jobs;

import cn.edu.zzti.bibased.service.operation.other.PositionNumDayService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class PositionNumDayJob extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(PositionNumDayJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        logger.info("lagou numDays start!!!");
        PositionNumDayService lagouService =(PositionNumDayService) applicationContext.getBean("positionNumDayService");
        lagouService.numDays();
        logger.info("lagou numDays end!!!");
    }
}
