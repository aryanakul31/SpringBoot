package com.nakul.user.dto

data class BaseResponse<T>(
    val response: T?,
    val message: String = "Ok",
    val status: Int = 200,
)
