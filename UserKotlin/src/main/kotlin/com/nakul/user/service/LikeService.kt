//package com.nakul.user.service
//
//
//import com.nakul.user.entities.Like
//import com.nakul.user.repo.PostReactionRepo
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.stereotype.Service
//
//@Service
//class LikeService {
//
//    @Autowired
//    private lateinit var likeRepo: PostReactionRepo
//
//    fun read(user: Int): List<Like> {
//        return likeRepo.findByUserId(user)
//    }
//
//    fun read(user: Int, id: Int): Like {
//        return likeRepo.findByIdAndUserId(id = id, user = user) ?: throw NoSuchElementException()
//    }
//
//    fun save(user: Int, postId: Int, data: Map<String, Any>): Like {
//        val prevLike = likeRepo.findByPostIdAndUserId(postId = postId, user = user)
//        return if (prevLike != null) likeRepo.save(prevLike)
//        else {
//            val post = Like(
//                user = user,
//                postId = postId,
//            )
//            likeRepo.save(post)
//        }
//    }
//
////    fun update(user: Int, likeId: Int, data: Map<String, Any>): Like {
////        val post = likeRepo.findByIdAndUserId(id = likeId, user = user) ?: throw NoSuchElementException()
////        if (data.containsKey("title")) post.title = data["title"] as String
////        if (data.containsKey("description")) post.description = data["description"] as String
////
////        return likeRepo.save(post)
////    }
//
//    fun delete(user: Int, likeId: Int): Like {
//        val post = likeRepo.findByIdAndUserId(id = likeId, user = user) ?: throw NoSuchElementException()
//        likeRepo.deleteById(post.id)
//        return post
//    }
//}