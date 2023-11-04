//package com.nakul.user.controller
//
//import com.nakul.user.entities.Comment
//import com.nakul.user.service.CommentService
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.transaction.annotation.Transactional
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api/comment")
//class CommentController {
//
//    @Autowired
//    private lateinit var commentService: CommentService
//
//    @GetMapping("")
//    fun getComment(httpRequest: HttpServletRequest): List<Comment> {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return commentService.read(user = user)
//    }
//
//    @GetMapping("/{id}")
//    fun getCommentById(httpRequest: HttpServletRequest, @PathVariable("id") commentId: Int): Comment {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return commentService.read(user = user, id = commentId)
//    }
//
//    @PutMapping("/{id}")
//    @Transactional
//    fun updateComment(
//        httpRequest: HttpServletRequest, @PathVariable("id") commentId: Int, @RequestBody data: Map<String, Any>
//    ): Comment {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return commentService.update(user = user, commentId = commentId, data = data)
//    }
//
//    @PostMapping("/{id}")
//    @Transactional
//    fun saveComment(
//        httpRequest: HttpServletRequest, @PathVariable("id") postId: Int, @RequestBody data: Map<String, Any>
//    ): Comment {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//
//        return commentService.save(user = user, postId = postId, data = data)
//    }
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    fun deleteComment(
//        httpRequest: HttpServletRequest, @PathVariable("id") commentId: Int
//    ): Comment {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return commentService.delete(user = user, commentId = commentId)
//    }
//
//}