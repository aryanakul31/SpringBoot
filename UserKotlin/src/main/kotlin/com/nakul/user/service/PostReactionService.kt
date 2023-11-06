package com.nakul.user.service


import com.nakul.user.dto.request.PostReactionRequestDTO
import com.nakul.user.dto.response.PostReactionResponseDTO
import com.nakul.user.entities.PostReaction
import com.nakul.user.repo.PostReactionRepo
import com.nakul.user.repo.PostRepo
import com.nakul.user.repo.UserRepo
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostReactionService {

    @Autowired
    private lateinit var postReactionRepo: PostReactionRepo

    @Autowired
    private lateinit var postRepo: PostRepo

    @Autowired
    private lateinit var userRepo: UserRepo

    fun read(postId: Int): List<PostReactionResponseDTO> {
        return postReactionRepo.findByPost_PostId(postId).map { it.getMap() }
    }

    fun save(userId: Int, postId: Int, postReactionRequestDTO: PostReactionRequestDTO): PostReactionResponseDTO {
        val post = postRepo.findById(postId).get()
        val user = userRepo.findById(userId).get()

        val response = PostReaction(
            post = post,
            user = user,
            comment = postReactionRequestDTO.comment
        )

        return postReactionRepo.save(response).getMap()
    }

    fun delete(userId: Int, postReactionId: Int): PostReactionResponseDTO {

        val reaction =
            postReactionRepo.findByPostReactionIdAndUser_UserId(postReactionId = postReactionId, userId = userId).get()

        postReactionRepo.deleteById(postReactionId)
        return reaction.getMap()
    }


//    fun getLikes(postId: Int): List<PostReactionResponseDTO> {
//        return postReactionRepo.findLikes(postId).map {
//            it.getMap()
//        }
//    }
//
//    fun getComments(postId: Int): List<PostReactionResponseDTO> {
//        return postReactionRepo.findComments(postId).map {
//            it.getMap()
//        }
//    }


    @Autowired
    private lateinit var mapper: ModelMapper


    fun PostReaction.getMap(): PostReactionResponseDTO {
        return mapper.map(this, PostReactionResponseDTO::class.java)
    }
}