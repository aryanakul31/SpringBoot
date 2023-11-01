package com.nakul.user.misc

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Component
@Order(2)
class LoggingFilter : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        LoggingAspect.LOGGER.info(
            "Request ===>  {} : {}", req.method,
            req.requestURI
        )
        chain?.doFilter(request, response)
//        LoggingAspect.LOGGER.info(
//            "Response ===> :{}",
//            res.status
//        )
    }

}


