package com.nakul.user.repo

import com.nakul.user.entities.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PostRepo : JpaRepository<Post, Int> {
    fun findByUser_UserId(userId: Int): List<Post>

    fun findByPostIdAndUser_UserId(postId: Int, userId: Int): Post

}
