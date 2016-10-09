package com.aidar.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by paradise on 03.05.16.
 */
@Component
@Aspect
public class LogAspect {

    private final static Logger logger = Logger.getLogger(LogAspect.class);

    private Object logMethodInvocation(ProceedingJoinPoint jp) throws Throwable {
        StringBuilder info = new StringBuilder();
        long start = System.currentTimeMillis();
        Object result = jp.proceed();
        info.append(jp.getTarget().getClass().getSimpleName()).append(".")
                .append(jp.getSignature().getName())
                .append("(").append(Arrays.toString(jp.getArgs())).append(")")
                .append(" : ").append(result)
                .append(" in ").append(System.currentTimeMillis() - start).append(" msec.");
        logger.info(info);
        return result;
    }

    @Around("execution(* com.aidar.repository.*.*(..))")
    public Object logRepository(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }

    @Around("execution(* com.aidar.service.*.*(..))")
    public Object logService(ProceedingJoinPoint jp) throws Throwable {
        return logMethodInvocation(jp);
    }

}
