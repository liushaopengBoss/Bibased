package cn.edu.zzti.bibased.aspect;


import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * INFO:用于监控慢接口
 */

@Aspect
@Component
public class LongTimeAspect {
    private static final Logger longTimeLogger = LoggerFactory.getLogger("longTime");
    private static final int MS_THRESHOLD = 300;//毫秒阈值

    @Around("execution(public * cn.edu.zzti.bibased.service.operation.*.*.*(..))")
    public Object aroundService(ProceedingJoinPoint pjp) throws Throwable {
        return longTimeLog(pjp, MS_THRESHOLD);
    }

    /**
     * 记录方法的时间
     *
     * @param pjp      ProceedingJoinPoint
     * @param longTime 超时需记录日志的时间
     * @return 执行结果
     * @throws Throwable 异常
     */
    private Object longTimeLog(ProceedingJoinPoint pjp, int longTime) throws Throwable {
        Map<String, Object> alertInfo = new HashMap<>();
        long start = System.currentTimeMillis();
        Object rs = pjp.proceed();
        long escape = System.currentTimeMillis() - start;
        alertInfo.put("escape", escape);
        StringBuilder sb;
        if (escape >= longTime) {
            sb = new StringBuilder(128);
            sb.append(pjp.getSignature().getDeclaringTypeName());
            sb.append(" ");
            sb.append(pjp.getSignature().getName());
            alertInfo.put("interfaceInfo", sb.toString());
            // 添加方法的参数, 方便调试
            Object[] params = pjp.getArgs();
            Gson gson = new Gson();
            if (params != null && params.length > 0) {
                //kibana 不支持多类型数组,参数处理成字符串
                String paramsStr =gson.toJson(params);
                paramsStr = StringUtils.replace(paramsStr, "\"", "");
                alertInfo.put("params", paramsStr);
            }
            longTimeLogger.info(gson.toJson(alertInfo), "");
        }
        return rs;
    }


}
