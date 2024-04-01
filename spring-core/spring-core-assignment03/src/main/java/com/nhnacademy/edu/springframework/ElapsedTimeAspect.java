package com.nhnacademy.edu.springframework;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ElapsedTimeAspect {

    @Pointcut("@annotation(Message)")
    public void targetGreeter() {
    }

    @Around("targetGreeter()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch stopWatch = new StopWatch("Logging Tracker");
        try {
            stopWatch.start();
            Object retVal = pjp.proceed();

            return retVal;
        } finally {
            stopWatch.stop();
            System.out.println(
                    "[ " + pjp.getTarget().getClass().getName() + " ] [ " + pjp.getSignature().getName() + " ] [ " +
                            stopWatch.getTotalTimeMillis() + " ]ms");

        }
    }
}
