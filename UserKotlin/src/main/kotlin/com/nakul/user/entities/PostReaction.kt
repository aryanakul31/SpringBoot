package com.nakul.user.entities

import jakarta.persistence.*


@Entity
@Table
data class PostReaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val reactionId: Int = 0,

    var userId: Int,
    var postId: Int,
    var comment: String? = null,
    var isLiked: Boolean = false,
) {
    override fun toString(): String {
        return "PostReaction(reactionId=$reactionId, userId=$userId, postId=$postId, comment=$comment, isLiked=$isLiked)"
    }
}
