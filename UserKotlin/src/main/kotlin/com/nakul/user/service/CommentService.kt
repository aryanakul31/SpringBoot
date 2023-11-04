//package com.nakul.user.service
//
//
//import com.nakul.user.entities.Comment
//import com.nakul.user.repo.CommentRepo
//import com.nakul.user.repo.PostRepo
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//import kotlin.jvm.optionals.getOrNull
//
//@Service
//class CommentService {
//
//    @Autowired
//    private lateinit var commentRepo: CommentRepo
//
//    @Autowired
//    private lateinit var postRepo: PostRepo
//
//    fun read(user: Int): List<Comment> {
//        return commentRepo.findByUserId(user)
//    }
//
//    fun read(user: Int, id: Int): Comment {
//        return commentRepo.findByIdAndUserId(id = id, user = user) ?: throw NoSuchElementException()
//    }
//
//    fun save(user: Int, postId: Int, data: Map<String, Any>): Comment {
//        val post = postRepo.findById(postId).getOrNull() ?: throw NoSuchElementException()
//        val parentComment = if (data.containsKey("commentId")) {
//            val commentId = data["commentId"] as Int
//            read(user = user, id = commentId)
//        } else null
//
//        val comment = Comment(
//            user = user, post = post, text = data["text"] as String, commentId = parentComment?.id
//        )
//        return commentRepo.save(comment)
//    }
//
//    fun update(user: Int, commentId: Int, data: Map<String, Any>): Comment {
//        val comment = commentRepo.findByIdAndUserId(id = commentId, user = user) ?: throw NoSuchElementException()
//
//        if (data.containsKey("text")) comment.text = data["text"] as String
//        if (data.containsKey("commentId")) comment.commentId = data["commentId"] as Int
//
//        return commentRepo.save(comment)
//    }
//
//    fun delete(user: Int, commentId: Int): Comment {
//        val post = commentRepo.findByIdAndUserId(id = commentId, user = user) ?: throw NoSuchElementException()
//        commentRepo.deleteById(post.id)
//        return post
//    }
//}