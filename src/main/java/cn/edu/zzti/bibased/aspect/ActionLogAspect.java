package cn.edu.zzti.bibased.aspect;

import cn.edu.zzti.bibased.constant.ProjectItem;
import cn.edu.zzti.bibased.dto.ActionLogDO;
import cn.edu.zzti.bibased.service.email.EmailService;
import cn.edu.zzti.bibased.service.operation.other.ActionLogService;
import cn.edu.zzti.bibased.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * INFO:行为数据统计 lagou
 */
@Aspect
@Component
public class ActionLogAspect {
    private Logger logger = LoggerFactory.getLogger(ActionLogAspect.class);
    @Resource
    private ActionLogService actionLogService;
    @Resource
    private EmailService emailService;

    @Around("execution(public * cn.edu.zzti.bibased.service.operation.lagou.*.*(..))")
    public Object aroundService(ProceedingJoinPoint pjp) throws Throwable {
        return addActionLog(pjp);
    }

    private Object addActionLog(ProceedingJoinPoint joinPoint) throws Throwable{
        Signature signature = joinPoint.getSignature();
        ProjectItem  actionLogName = null ;
        if (signature instanceof MethodSignature) {
            Method method = ((MethodSignature) signature).getMethod();
            ActionLog actionLog = method.getAnnotation(ActionLog.class);
            if(actionLog!=null){
                actionLogName = actionLog.value();
            }
        }
        if(actionLogName != null){
            String start = DateUtils.formatStr(new Date(), DateUtils.YYMMDD_HHmmStr);
            Object rs = joinPoint.proceed();
            String end = DateUtils.formatStr(new Date(), DateUtils.YYMMDD_HHmmStr);
            ActionLogDO actionLogDO = new ActionLogDO();
            actionLogDO.setActionName(actionLogName.getName());
            actionLogDO.setEndTime(end);
            actionLogDO.setStartTime(start);
            actionLogDO.setStatus(1);
            actionLogDO.setTypeCode(actionLogName.getCode());
            actionLogDO.setInclude("lagou");
            actionLogService.addLog(actionLogDO);
//            emailService.sendSimpleMail("","");
            return rs;
        }
        Object rs = joinPoint.proceed();

        return rs;
    }
}
