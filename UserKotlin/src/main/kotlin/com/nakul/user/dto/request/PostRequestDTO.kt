package com.nakul.user.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostRequestDTO(
    @field:NotBlank(message = "Empty Title")
    @field:Size(min = 5, max = 50, message = "10 - 50 limit")

    val title: String = "",

    @field:NotBlank(message = "Empty Description")
    @field:Size(min = 10, max = 200, message = "10 - 200 limit")
    val description: String = "",
)
