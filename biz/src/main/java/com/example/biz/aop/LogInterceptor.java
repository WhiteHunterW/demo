package com.example.biz.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Function:
 *
 * @author xingche
 * @date 2023/2/16
 */
@Aspect
@Component
public class LogInterceptor {


    /**
     * 切入点定义路径
     */
    @Pointcut(value = "execution(public * com.example.biz.controller.AsyncController.test*())")
    public void executeAsync() {
    }

    @Before(value = "execution(public * com.example.biz.controller.AsyncController.*())")
    public void log() {
        System.out.println("记录日志");
    }

    @Around("executeAsync()")
    public void logAspect(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("LogInterceptor.logAspect");
        pjp.proceed();
    }

    @Around("@annotation(logTest)")
    public void logInterceptor(ProceedingJoinPoint ppj, LogTest logTest) throws Throwable {
        String name = logTest.value();
        System.out.println(name);
        ppj.proceed();
    }

}
