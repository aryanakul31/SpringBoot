package com.nakul.user.service


import com.nakul.user.dto.request.PostReactionRequestDTO
import com.nakul.user.dto.response.PostReactionResponseDTO
import com.nakul.user.entities.PostReaction
import com.nakul.user.repo.PostReactionRepo
import com.nakul.user.repo.PostRepo
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PostReactionService {

    @Autowired
    private lateinit var postReactionRepo: PostReactionRepo

    @Autowired
    private lateinit var postRepo: PostRepo

//    fun read(user: Int): List<PostRe> {
//        return likeRepo.findByUserId(user)
//    }
//
//    fun read(user: Int, id: Int): Like {
//        return likeRepo.findByIdAndUserId(id = id, user = user) ?: throw NoSuchElementException()
//    }

    fun save(userId: Int, postId: Int, postReactionRequestDTO: PostReactionRequestDTO): PostReactionResponseDTO {
        val post = postRepo.findById(postId).get()

        val response = when {
            /**Like Case*/
            postReactionRequestDTO.comment.isNullOrBlank() -> {
                var likeId = postReactionRepo.findLike(postId = postId, userId = userId)

                if (likeId == null)
                    likeId = PostReaction(
                        isLiked = false,
                        postId = postId,
                        userId = userId,
                    )

                likeId.isLiked = postReactionRequestDTO.isLiked

                postReactionRepo.save(likeId)
            }

            else -> {
                val reaction = PostReaction(
                    isLiked = false, postId = postId, userId = userId, comment = postReactionRequestDTO.comment
                )
                postReactionRepo.save(reaction)
            }
        }
        return response.getMap()
    }

    fun delete(userId: Int, postReactionId: Int): PostReactionResponseDTO {

        val reaction = postReactionRepo.findByPostReactionIdAndUserId(postReactionId = postReactionId, userId = userId)
            ?: throw NoSuchElementException()

        postReactionRepo.deleteById(postReactionId)
        return reaction.getMap()
    }


    fun getLikes(postId: Int): List<PostReactionResponseDTO> {
        return postReactionRepo.findLikes(postId).map {
            it.getMap()
        }
    }

    fun getComments(postId: Int): List<PostReactionResponseDTO> {
        return postReactionRepo.findComments(postId).map {
            it.getMap()
        }
    }


    @Autowired
    private lateinit var mapper: ModelMapper

    @Autowired
    private lateinit var userService: UserService

    fun PostReaction.getMap(): PostReactionResponseDTO {
        val data = mapper.map(this, PostReactionResponseDTO::class.java)
        data.user = userService.read(userId)

        return data
    }

}