package cn.edu.zzti.bibased.jobs;

import cn.edu.zzti.bibased.dao.cache.Cache;
import cn.edu.zzti.bibased.service.operation.lagou.LagouService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 清除过期缓存
 *
 * Created by huaidou on  2018/3/26
 */
public class ClearCache extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(LagouJob.class);
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = (ApplicationContext) context.getJobDetail().getJobDataMap()
                .get("applicationContext");
        Cache cache =(Cache) applicationContext.getBean("cache");
        cache.clearInvalideData();
        logger.info("lagou job clearcache start !!");
    }
}
