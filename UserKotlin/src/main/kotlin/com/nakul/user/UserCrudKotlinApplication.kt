package com.nakul.user

import com.nakul.user.security.JwtFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean


@SpringBootApplication
class UserCrudKotlinApplication {
    @Autowired
    lateinit var beanFactory: AutowireCapableBeanFactory

    @Bean
    fun myFilter(): FilterRegistrationBean<JwtFilter> {
        val registration = FilterRegistrationBean<JwtFilter>()
        val myFilter = JwtFilter()
        beanFactory.autowireBean(myFilter)
        registration.filter = myFilter
        registration.addUrlPatterns("/api/address/*", "/api/post/*", "/api/like/*", "/api/comment/*")
        registration.order = 1
        return registration
    }

}

fun main(args: Array<String>) {
    runApplication<UserCrudKotlinApplication>(*args)
}


