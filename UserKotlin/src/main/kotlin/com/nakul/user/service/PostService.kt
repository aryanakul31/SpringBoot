package com.nakul.user.service

import com.nakul.user.dto.request.PostRequestDTO
import com.nakul.user.dto.response.PostResponseDTO
import com.nakul.user.entities.Post
import com.nakul.user.repo.PostRepo
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PostService {

    @Autowired
    private lateinit var postRepo: PostRepo

    fun read(userId: Int, isMine: Boolean): List<PostResponseDTO> {
        return if (isMine)
            postRepo.findByUserId(userId).map { it.getMap() }
        else
            postRepo.findAll().map { it.getMap() }
    }

    fun read(userId: Int, postId: Int, isMine: Boolean): PostResponseDTO {
        return if (isMine)
            postRepo.findByPostIdAndUserId(postId = postId, userId = userId)?.getMap() ?: throw NoSuchElementException()
        else
            postRepo.findById(postId).getOrNull()?.getMap()?.apply {
//            comment = comment.filter { it.commentId == null }.toSet()
            } ?: throw NoSuchElementException()
    }

    fun save(userId: Int, postRequestDTO: PostRequestDTO): PostResponseDTO {
        val post = Post(
            userId = userId,
            title = postRequestDTO.title,
            description = postRequestDTO.description,
        )
        return postRepo.save(post).getMap()
    }

    fun update(userId: Int, postId: Int, postRequestDTO: PostRequestDTO): PostResponseDTO {
        val post = postRepo.findByPostIdAndUserId(postId = postId, userId = userId) ?: throw NoSuchElementException()

        if (postRequestDTO.title.isNotBlank()) post.title = postRequestDTO.title
        if (postRequestDTO.description.isNotBlank()) post.description = postRequestDTO.description


        return postRepo.save(post).getMap()
    }

    fun delete(user: Int, addressId: Int): PostResponseDTO {
        val post = postRepo.findByPostIdAndUserId(postId = addressId, userId = user) ?: throw NoSuchElementException()
        postRepo.deleteById(post.postId)
        return post.getMap()
    }

    fun Post.getMap(): PostResponseDTO {
        val mapper = ModelMapper()
        val data = mapper.map(this, PostResponseDTO::class.java)
//todo
//        data.reaction = addressRepo.findByUserId(userId).toSet()
        return data
    }
}