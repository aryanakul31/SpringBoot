package com.nakul.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserCrudKotlinApplication

fun main(args: Array<String>) {
	runApplication<UserCrudKotlinApplication>(*args)
}
