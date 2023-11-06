package com.nakul.user.entities

import jakarta.persistence.*

@Entity
@Table
data class Post(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val postId: Int = 0,
    var title: String,
    var description: String,

    @ManyToOne
    @JoinColumn(name = "user_fk")
    var user: User,

    @OneToMany(
        mappedBy = "post",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    ) val postReaction: Set<PostReaction> = setOf()
) {
    override fun toString(): String {
        return "Post(postId=$postId, title='$title', description='$description', user=$user, postReaction=$postReaction)"
    }
}