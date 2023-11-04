package com.nakul.user.dto.response

import com.nakul.user.entities.Address

data class UserResponseDTO(
    var userId: Int = 0,
    var name: String? = "",
    var email: String = "",
    var token: String? = null,
    var addresses: Set<Address> = setOf()
)