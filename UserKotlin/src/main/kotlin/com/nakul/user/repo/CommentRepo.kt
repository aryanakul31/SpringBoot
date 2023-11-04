//package com.nakul.user.repo
//
//import com.nakul.user.entities.Comment
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.stereotype.Repository
//
//
//@Repository
//interface CommentRepo : JpaRepository<Comment, Int> {
//    fun findByUserId(user: Int): List<Comment>
//    fun findByIdAndUserId(id: Int, user: Int): Comment?
//    fun findByPostIdAndUserId(postId: Int, user: Int): Comment?
//}
