package org.example.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class PrintLogAspect {

    @Pointcut("execution(* org.example.demo.controller.*.*(..))")
    public void pointcut() {}

    @Before("pointcut() && @annotation(org.example.demo.aop.PrintLog)")
    public void before(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("*************** PrintLogAspect.before() ***************");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @After("pointcut() && @annotation(org.example.demo.aop.PrintLog)")
    public void after(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("*************** PrintLogAspect.after() ***************");
    }

    @Around("pointcut() && @annotation(org.example.demo.aop.PrintLog)")
    public void around(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("*************** PrintLogAspect.around() ***************");
    }

    @AfterReturning("pointcut() && @annotation(org.example.demo.aop.PrintLog)")
    public void afterReturning(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("*************** PrintLogAspect.afterReturning() ***************");
    }

    @AfterThrowing("pointcut() && @annotation(org.example.demo.aop.PrintLog)")
    public void afterThrowing(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("*************** PrintLogAspect.afterThrowing() ***************");
    }

}
