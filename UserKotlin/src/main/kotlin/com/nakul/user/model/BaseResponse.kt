package com.nakul.user.model

import org.springframework.http.HttpStatus

data class BaseResponse<T>(
    val response: T?,
    val message: String,
    val responseCode: Int,
    @Transient
    val httpStatus: HttpStatus
)
