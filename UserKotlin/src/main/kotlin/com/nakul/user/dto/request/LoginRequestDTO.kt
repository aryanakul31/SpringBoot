package com.nakul.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class LoginRequestDTO(
    @field:NotBlank(message = "Empty Email")
    @field:Email(message = "Invalid Email")
    var email: String = "",

    @field:NotBlank(message = "Empty Password")
    val password: String = "",
)