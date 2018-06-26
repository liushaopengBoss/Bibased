package cn.edu.zzti.bibased.aspect;

import java.lang.annotation.*;

/**
 * 限制流量
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceLimit {
    String description() default  "";
}
