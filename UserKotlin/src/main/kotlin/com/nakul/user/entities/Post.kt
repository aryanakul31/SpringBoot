package com.nakul.user.entities

import jakarta.persistence.*

@Entity
@Table
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Int = 0,
    var title: String,
    var description: String,
    var userId: Int,
) {
    override fun toString(): String {
        return "Post(postId=$postId, title='$title', description='$description', userId=$userId)"
    }
}