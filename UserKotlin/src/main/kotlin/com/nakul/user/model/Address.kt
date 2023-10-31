package com.nakul.user.model

import jakarta.persistence.*

@Entity
@Table
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Int,
    val name: String,
    val lat: Double,
    val long: Double,
) {
    override fun toString(): String {
        return "Address(id=$id, name='$name', lat=$lat, long=$long)"
    }
}
