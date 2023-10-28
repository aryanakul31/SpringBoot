package com.nakul.user.misc

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.Customizer
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
//import org.springframework.security.web.SecurityFilterChain


//@Configuration
//class SecurityConfig{
//    @Bean
//    @Throws(Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .authorizeHttpRequests(
//                Customizer<AuthorizationManagerRequestMatcherRegistry> { authz: AuthorizationManagerRequestMatcherRegistry ->
//                    authz
//                        .anyRequest().authenticated()
//                }
//            )
//            .httpBasic(withDefaults())
//        return http.build()
//    }
//}