package com.nakul.user.dto

import com.nakul.user.entities.Address

data class UserDTO(
    var id: Int = 0,
    val name: String = "",
    var email: String = "",
    var password: String = "",
    var token: String? = null,
    val addresses: Set<Address> = setOf()
)