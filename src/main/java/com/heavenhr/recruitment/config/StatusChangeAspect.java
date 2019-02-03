package com.heavenhr.recruitment.config;

import com.heavenhr.recruitment.entity.ApplicationStatus;
import com.heavenhr.recruitment.service.application.StatusChangeService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shridhar on 02/02/19.
 */

@Aspect
@Configuration
public class StatusChangeAspect {

    private final StatusChangeService statusChangeService;

    public StatusChangeAspect(StatusChangeService statusChangeService) {
        this.statusChangeService = statusChangeService;
    }

    @After(value = "execution(* com.heavenhr.recruitment.service.application.impl.ApplicationServiceImpl.progressStatus(..))")
    public void after(JoinPoint joinPoint) {
        final Object[] args = joinPoint.getArgs();
        statusChangeService.after((Long) args[0], (ApplicationStatus) args[1]);
    }
}
