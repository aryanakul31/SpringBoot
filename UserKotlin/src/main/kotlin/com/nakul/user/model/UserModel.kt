package com.nakul.user.model

import jakarta.persistence.*

@Entity
@Table(name = "UserModel")
data class UserModel(
    @Column @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int,

    @Column
    val name: String,

    @Column(unique = true)
    var email: String,

    @Column
    var password: String,
) {
    override fun toString(): String {
        return "UserModel(id=$id, name='$name', email='$email', password='$password')"
    }
}
