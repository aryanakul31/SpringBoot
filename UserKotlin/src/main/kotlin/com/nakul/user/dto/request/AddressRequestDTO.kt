package com.nakul.user.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class AddressRequestDTO(
    @field:NotBlank(message = "Empty Name")
    @field:NotNull(message = "Blank Name")
    val name: String? = null,

    @field:NotBlank(message = "Blank Description")
    @field:NotEmpty(message = "Empty Description")
    val description: String? = null,

    @field:NotNull(message = "Empty latitude")
    val lat: Double? = null,

    @field:NotNull(message = "Empty long")
    val long: Double? = null,
)