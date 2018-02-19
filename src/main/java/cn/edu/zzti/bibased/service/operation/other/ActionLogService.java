package cn.edu.zzti.bibased.service.operation.other;

import cn.edu.zzti.bibased.dto.ActionLogDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ActionLogService {
    private static final Logger lgger = LoggerFactory.getLogger(ActionLogService.class);
    public void addLog(ActionLogDO actionLogDO){
        lgger.info("actionLog"+actionLogDO);
    }
}
