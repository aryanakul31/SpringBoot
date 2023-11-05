package com.nakul.user.dto.response

data class UserResponseDTO(
    var userId: Int = 0,
    var name: String? = "",
    var email: String = "",
    var token: String? = null,
    var addresses: List<AddressResponseDTO>? = null
)