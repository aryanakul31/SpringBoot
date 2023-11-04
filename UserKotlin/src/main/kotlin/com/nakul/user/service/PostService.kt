package com.nakul.user.service

import com.nakul.user.dto.request.PostRequestDTO
import com.nakul.user.entities.Post
import com.nakul.user.repo.PostRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class PostService {

    @Autowired
    private lateinit var postRepo: PostRepo

    fun read(userId: Int, isMine: Boolean): List<Post> {
        return if (isMine)
            postRepo.findByUserId(userId)
        else
            postRepo.findAll()
    }

    fun read(userId: Int, postId: Int, isMine: Boolean): Post {
        return if (isMine)
            postRepo.findByPostIdAndUserId(postId = postId, userId = userId) ?: throw NoSuchElementException()
        else
            postRepo.findById(postId).getOrNull()?.apply {
//            comment = comment.filter { it.commentId == null }.toSet()
            } ?: throw NoSuchElementException()
    }

    fun save(userId: Int, postRequestDTO: PostRequestDTO): Post {
        val post = Post(
            userId = userId,
            title = postRequestDTO.title,
            description = postRequestDTO.description,
        )
        return postRepo.save(post)
    }

    fun update(userId: Int, postId: Int, postRequestDTO: PostRequestDTO): Post {
        val post = postRepo.findByPostIdAndUserId(postId = postId, userId = userId) ?: throw NoSuchElementException()

        if (postRequestDTO.title.isNotBlank()) post.title = postRequestDTO.title
        if (postRequestDTO.description.isNotBlank()) post.description = postRequestDTO.description


        return postRepo.save(post)
    }

    fun delete(user: Int, addressId: Int): Post {
        val post = postRepo.findByPostIdAndUserId(postId = addressId, userId = user) ?: throw NoSuchElementException()
        postRepo.deleteById(post.postId)
        return post
    }
}