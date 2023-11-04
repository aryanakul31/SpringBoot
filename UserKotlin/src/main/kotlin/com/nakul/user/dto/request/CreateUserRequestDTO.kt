package com.nakul.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size


data class CreateUserRequestDTO(
    @field:NotBlank(message = "Empty Name")
    val name: String = "",

    @field:NotBlank(message = "Empty Email")
    @field:Email(message = "Invalid Email")
    var email: String = "",

    @field:NotBlank(message = "Empty Password")
    @field:Size(min = 8, message = "password should have at least 8 characters")
    val password: String = "",
)