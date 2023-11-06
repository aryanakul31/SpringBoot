package com.nakul.user.entities

import jakarta.persistence.*


@Entity
@Table
data class PostReaction(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postReactionId: Int = 0,

    var comment: String? = null,

    @ManyToOne @JoinColumn(name = "post_fk") var post: Post,

    @ManyToOne @JoinColumn(name = "user_fk") var user: User,
) {
    override fun toString(): String {
        return "PostReaction(postReactionId=$postReactionId, comment=$comment, post=$post, user=$user)"
    }
}
