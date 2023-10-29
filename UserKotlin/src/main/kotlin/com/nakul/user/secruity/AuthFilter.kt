//package com.nakul.user.secruity
//
//import jakarta.servlet.FilterChain
//import jakarta.servlet.ServletRequest
//import jakarta.servlet.ServletResponse
//import jakarta.servlet.http.HttpServletRequest
//import jakarta.servlet.http.HttpServletResponse
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.http.HttpStatus
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.web.filter.GenericFilterBean
//
//
//@Configuration
//class AuthFilter : GenericFilterBean() {
//
//
//
//    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
//        val httpServletRequest = p0 as HttpServletRequest
//        val httpServletResponse = p1 as HttpServletResponse
//        val authToken = httpServletRequest.getHeader("Authorization")
//        when {
//            authToken.isNullOrBlank() -> httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Token Missing")
//            authToken.contains("Bearer ") -> {
//                try {
//                    val token = authToken.split("Bearer ")[1]
//                    val userId = JwtUtil.checkToken(token)
//                    httpServletRequest.setAttribute("userId", userId)
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid/Expired Token")
//                }
//            }
//
//            else -> httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(), "Token Invalid (Bearer Missing")
//        }
//        p2?.doFilter(p0, p1)
//    }
//}