package com.example.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 拦截 interface 使用java动态代码
 */
@Aspect
@Component
public class ServiceRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(ServiceRequestAspect.class);

    @Pointcut("execution(public * com.example.springaop.service.*.*(..))")
    private void allMethod() {

    }

    @Around("allMethod()")
    public Object doAround(ProceedingJoinPoint call) throws Throwable {
        logger.info("====================================== aspect ======================================");
        Object[] args = call.getArgs();
        try {
            return call.proceed();
        } catch (Exception e) {
            logger.warn("", e);
            return "";
        } finally {
            logger.info("====================================================================================");
        }
    }
}
