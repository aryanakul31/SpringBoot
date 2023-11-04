package com.nakul.user.entities

import jakarta.persistence.*

@Entity
@Table
data class Address(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val addressId: Int = 0,

    @Column(unique = true)
    var name: String?,
    var description: String?=null,
    var lat: Double?,
    var long: Double?,

//TODO (Foreign Key Constraint)
    var userId: Int?,

//    @ManyToOne
//    @JsonBackReference
//    @JoinColumn(name = "user_id")
//    var user: User? = null
) {
    override fun toString(): String {
        return "Address(addressId=$addressId, name='$name', lat=$lat, long=$long)"
    }
}
