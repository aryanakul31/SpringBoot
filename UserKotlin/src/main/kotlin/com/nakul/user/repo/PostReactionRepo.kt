package com.nakul.user.repo

import com.nakul.user.entities.PostReaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostReactionRepo : JpaRepository<PostReaction, Int> {
    fun findByUserId(user: Int): List<PostReaction>
    fun findByPostId(user: Int): List<PostReaction>
    fun findByPostReactionIdAndUserId(postReactionId: Int, userId: Int): PostReaction?

    @Query(
        "select  * from post_reaction where post_id= :postId and user_id = :userId and comment is null",
        nativeQuery = true,
    )
    fun findLike(postId: Int, userId: Int): PostReaction?

    @Query(
        "select  * from post_reaction where post_id= :postId and comment is null",
        nativeQuery = true,
    )
    fun findLikes(postId: Int): List<PostReaction>

    @Query(
        "select  * from post_reaction where post_id= :postId and comment is not null",
        nativeQuery = true,
    )
    fun findComments(postId: Int): List<PostReaction>


//    @Query(
//        "select  * from post_reaction where post_id= :postId and user_id = :userId and comment is null",
//        nativeQuery = true,
//    )
//    fun findComment(postId: Int, userId: Int): PostReaction?
}
