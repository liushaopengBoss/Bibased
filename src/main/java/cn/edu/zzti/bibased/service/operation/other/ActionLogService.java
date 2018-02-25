package cn.edu.zzti.bibased.service.operation.other;

import cn.edu.zzti.bibased.dao.lagou.ActionLogDao;
import cn.edu.zzti.bibased.dto.ActionLogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ActionLogService {

    @Resource
    private ActionLogDao actionLogDao;
    private static final Logger lgger = LoggerFactory.getLogger(ActionLogService.class);
    @Async
    public void addLog(ActionLogDO actionLogDO){
        actionLogDao.insert(actionLogDO);
        lgger.info("actionLog"+actionLogDO);
    }
}
