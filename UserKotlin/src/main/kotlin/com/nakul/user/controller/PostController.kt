package com.nakul.user.controller

import com.nakul.user.dto.request.PostRequestDTO
import com.nakul.user.dto.response.PostResponseDTO
import com.nakul.user.entities.Post
import com.nakul.user.service.PostService
import jakarta.servlet.http.HttpServletRequest
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostController {

    @Autowired
    private lateinit var postService: PostService

    @GetMapping("")
    fun getPost(httpRequest: HttpServletRequest, ): List<PostResponseDTO> {
        val user = (httpRequest.getAttribute("user") as String).toInt()
        val isMine = httpRequest.getParameter("isMine").toBoolean()
        return postService.read(userId = user, isMine = isMine).map { it.getMap() }
    }

    @GetMapping("/{id}")
    fun getPostById(httpRequest: HttpServletRequest, @PathVariable("id") postId: Int,): PostResponseDTO {
        val user = (httpRequest.getAttribute("user") as String).toInt()
        val isMine = httpRequest.getParameter("isMine").toBoolean()
        return postService.read(userId = user, postId = postId,isMine=isMine).getMap()
    }

    @PutMapping("/{id}")
    @Transactional
    fun updatePost(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int, @RequestBody postRequestDTO: PostRequestDTO
    ): PostResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return postService.update(userId = userId, postId = addressId, postRequestDTO = postRequestDTO).getMap()
    }

    @PostMapping("")
    @Transactional
    fun savePost(
        httpRequest: HttpServletRequest, @RequestBody postRequestDTO: PostRequestDTO
    ): PostResponseDTO {
        val userId = (httpRequest.getAttribute("user") as String).toInt()
        return postService.save(userId = userId, postRequestDTO = postRequestDTO).getMap()
    }


    @DeleteMapping("/{id}")
    @Transactional
    fun deletePost(
        httpRequest: HttpServletRequest, @PathVariable("id") addressId: Int
    ): PostResponseDTO {
        val user = (httpRequest.getAttribute("user") as String).toInt()
        return postService.delete(user = user, addressId = addressId).getMap()
    }

    fun Post.getMap(): PostResponseDTO {
        val mapper = ModelMapper()
        val data = mapper.map(this, PostResponseDTO::class.java)
//todo
//        data.reaction = addressRepo.findByUserId(userId).toSet()
        return data
    }
}