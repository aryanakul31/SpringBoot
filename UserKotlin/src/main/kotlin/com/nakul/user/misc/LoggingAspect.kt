package com.nakul.user.misc

import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class LoggingAspect {
    companion object {
        val LOGGER = LoggerFactory.getLogger(LoggingAspect::class.java)
    }

    @Before("execution(* com.nakul.user.service.UserService..*(..))")
    fun logBefore() {
//        LOGGER.warn("logBefore testing")
    }

    @After("execution(* com.nakul.user.service.UserService..*(..))")
    fun logAfter() {
//        LOGGER.warn("logAfter testing")
    }

}