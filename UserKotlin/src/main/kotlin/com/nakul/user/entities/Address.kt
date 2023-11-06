package com.nakul.user.entities

import jakarta.persistence.*

@Entity
@Table
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val addressId: Int = 0,

    @Column(unique = true)
    var name: String?,
    var description: String? = null,
    var lat: Double?,
    var long: Double?,

    @ManyToOne
    @JoinColumn(name = "user_fk")
    var user: User,
) {
    override fun toString(): String {
        return "Address(addressId=$addressId, name='$name', lat=$lat, long=$long)"
    }
}
