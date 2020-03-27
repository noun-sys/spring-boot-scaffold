package com.noun.sys.scaffold.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author gaoxu @Date 2019-05-28 16:58
 */
@Component
@Aspect
public class LogableAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("@annotation(com.noun.sys.scaffold.base.aspect.annotation.Logable)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object exec(final ProceedingJoinPoint point) throws Throwable {
        Class clazz = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        String methodFullName = new StringBuilder(clazz.getCanonicalName()).append(".").append(methodName).toString();
        Object[] parameters = point.getArgs();

        try {
            log.info("begin method name = {}, parameters = {}", methodFullName, parameters);
            long startTime = System.currentTimeMillis();
            Object ret = point.proceed();
            long endTime = System.currentTimeMillis();
            log.info("end method name = {},result = {} ,cost time = {}ms", ret, (endTime - startTime));
            return ret;
        } catch (Exception ex) {
            log.error("method name = {} execute error", methodFullName, ex);
            throw ex;
        }
    }
}
