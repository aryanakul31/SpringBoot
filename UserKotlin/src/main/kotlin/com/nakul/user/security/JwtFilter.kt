package com.nakul.user.security

import com.nakul.user.misc.CustomException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.web.filter.GenericFilterBean


class JwtFilter : GenericFilterBean() {

    override fun doFilter(p0: ServletRequest?, p1: ServletResponse?, p2: FilterChain?) {
        val httpServletRequest = p0 as HttpServletRequest
        val httpServletResponse = p1 as HttpServletResponse

        try {
            val authToken = httpServletRequest.getHeader("Authorization")

            when {
                authToken.isNullOrBlank() -> throw CustomException(HttpStatus.UNAUTHORIZED, "Token Missing")
                authToken.contains("Bearer ") -> {
                    try {
                        val token = authToken.split("Bearer ")[1]
                        val data = JwtUtil.checkToken(token)

                        httpServletRequest.setAttribute("userId", data["id"].toString())
//                        val user = userRepo.findById(userId)
//                            .get() // new User(jwtUtil.extractUsername(jwt), jwtUtil.extractUserId(jwt));L
//                        val final = UsernamePasswordAuthenticationToken(user, token, user.authorities)
//                        SecurityContextHolder.getContext().authentication = final
                        p2?.doFilter(p0, p1)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        throw CustomException(HttpStatus.UNAUTHORIZED, "Invalid/Expired Token")
                    }
                }

                else -> throw CustomException(HttpStatus.UNAUTHORIZED, "Token Invalid (Bearer Missing")
            }
        } catch (exception: CustomException) {
//            throw CustomException(HttpStatus.UNAUTHORIZED, "Token Missing")
            httpServletResponse.sendError(exception.httpStatus.value(), exception.errorMessage)
        }
    }
}