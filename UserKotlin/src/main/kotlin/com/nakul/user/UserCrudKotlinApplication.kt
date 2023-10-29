package com.nakul.user

//import com.nakul.user.secruity.AuthFilter
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//import org.springframework.context.annotation.Bean
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.web.SecurityFilterChain

@SpringBootApplication
class UserCrudKotlinApplication {

//    @Bean
//    @Throws(java.lang.Exception::class)
//    fun filterChain(http: HttpSecurity): SecurityFilterChain {
//        return http.authorizeHttpRequests {
//            it.anyRequest().permitAll()
//        }.build()
//    }

//    @Bean
//    fun filterRegistrationBean(): FilterRegistrationBean<AuthFilter> {
//        val registrationBean: FilterRegistrationBean<AuthFilter> = FilterRegistrationBean<AuthFilter>()
//        val authFilter = AuthFilter()
//        registrationBean.setFilter(authFilter)
//        registrationBean.addUrlPatterns("/api/categories/*")
//        return registrationBean
//    }
}

fun main(args: Array<String>) {
    runApplication<UserCrudKotlinApplication>(*args)
}
