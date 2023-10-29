package com.nakul.user.dto

import com.fasterxml.jackson.annotation.JsonInclude

data class BaseResponse<T>(
    val response: T?,
    val message: String = "Ok",
    val status: Int = 200,
)
