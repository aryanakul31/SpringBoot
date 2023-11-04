package com.nakul.user.dto.response

data class AddressResponseDTO(
    var addressId: Int?=null,
    var name: String ?=null,
    var description: String?=null,
    var lat: Double ?=null,
    var long: Double ?=null,
)