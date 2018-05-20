package cn.edu.zzti.bibased.jobs;

import cn.edu.zzti.bibased.service.operation.other.PositionKeyWordSevice;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class LagouDataKeyWord extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(LagouDataKeyWord.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        logger.info("lagou keyword start!!!");
        PositionKeyWordSevice lagouService =(PositionKeyWordSevice) applicationContext.getBean("positionKeyWordSevice");
        lagouService.keyWord();
        logger.info("lagou keyword end!!!");
    }

}
