package com.nakul.user.model

import jakarta.persistence.*

@Entity
@Table
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Int = 0,
    var name: String,
    var lat: Double,
    var long: Double,
    var userId: Int,
) {
    override fun toString(): String {
        return "Address(id=$id, name='$name', lat=$lat, long=$long)"
    }
}
