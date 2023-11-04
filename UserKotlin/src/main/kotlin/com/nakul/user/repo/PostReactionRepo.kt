package com.nakul.user.repo

import com.nakul.user.entities.PostReaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostReactionRepo : JpaRepository<PostReaction, Int> {
    fun findByUserId(user: Int): List<PostReaction>
    fun findByPostId(user: Int): List<PostReaction>
    fun findByReactionIdAndUserId(reactionId: Int, userId: Int): PostReaction?
    fun findByPostIdAndUserId(postId: Int, userId: Int): PostReaction?
}
