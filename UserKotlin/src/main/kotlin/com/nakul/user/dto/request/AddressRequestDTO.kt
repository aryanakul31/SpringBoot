package com.nakul.user.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AddressRequestDTO(
    @field:NotBlank(message = "Empty Name")
    val name: String = "",

    @field:NotBlank(message = "Empty Description")
    val description: String = "",

    @field:NotNull(message = "Empty latitude")
    val lat: Double ?=0.0,

    @field:NotNull(message = "Empty long")
    val long: Double ?=0.0
)