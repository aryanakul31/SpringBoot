package com.nakul.user.controller

import com.nakul.user.dto.request.PostReactionRequestDTO
import com.nakul.user.dto.response.PostReactionResponseDTO
import com.nakul.user.service.PostReactionService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post/reaction")
class PostReactionController {

    @Autowired
    private lateinit var postReactionService: PostReactionService

    @PostMapping("/{id}")
    fun postLike(
        httpRequest: HttpServletRequest,
        @PathVariable("id") postId: Int,
        @RequestBody postReactionRequestDTO: PostReactionRequestDTO
    ): PostReactionResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return postReactionService.save(
            userId = userId,
            postId = postId,
            postReactionRequestDTO = postReactionRequestDTO
        )
    }

    @DeleteMapping("/{id}")
    fun deletePostReactionId(
        httpRequest: HttpServletRequest,
        @PathVariable("id") postReactionId: Int,
    ): PostReactionResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return postReactionService.delete(userId = userId, postReactionId = postReactionId)
    }
}