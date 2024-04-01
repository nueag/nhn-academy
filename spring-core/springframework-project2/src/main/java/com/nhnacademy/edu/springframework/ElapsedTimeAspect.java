package com.nhnacademy.edu.springframework;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class ElapsedTimeAspect {

    @Pointcut(
            "execution(* com.nhnacademy.edu.springframework.domain.*.*(..)) || execution(* com.nhnacademy.edu.springframework.report.*.*(..))" +
                    " || execution(* com.nhnacademy.edu.springframework.repository.*.*(..)) || execution(* com.nhnacademy.edu.springframework.service.*.*(..))")
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
            String line =
                    "[ " + pjp.getTarget().getClass().getName() + " ] [ " + pjp.getSignature().getName() + " ] [ " +
                            stopWatch.getTotalTimeMillis() + " ]ms\n";
            log.info(line);


        }
    }
}
