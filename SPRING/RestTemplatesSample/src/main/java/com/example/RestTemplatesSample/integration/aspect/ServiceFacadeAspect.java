package com.example.RestTemplatesSample.integration.aspect;

import com.example.RestTemplatesSample.integration.exception.RestException;
import com.example.RestTemplatesSample.integration.model.Result;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 拦截http请求，捕获异常信息
 */
@Aspect
@Component
public class ServiceFacadeAspect {

    @Pointcut("execution(* com.example.RestTemplatesSample.integration.service.*.*(..))")
    private void allMethod() {
    }

    @Around("allMethod()")
    public Object doAround(ProceedingJoinPoint call) throws Throwable {
        MethodSignature signature = (MethodSignature) call.getSignature();
        Method method = signature.getMethod();
        Result<?> baseRes = (Result<?>) method.getReturnType().newInstance();
        try {
            baseRes = (Result<?>) call.proceed();
            return baseRes;
        } catch (RestException e) {
            baseRes.setSuccess(false);
            baseRes.setCode(-1);
            baseRes.setMessage(e.getMessage());
            return baseRes;

        } catch (Exception e) {
            baseRes.setSuccess(false);
            baseRes.setCode(-100);
            baseRes.setMessage(e.getMessage());
            return baseRes;

        } finally {

        }
    }
}