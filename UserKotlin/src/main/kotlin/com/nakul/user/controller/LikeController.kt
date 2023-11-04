//package com.nakul.user.controller
//
//import com.nakul.user.entities.Like
//import com.nakul.user.service.LikeService
//import jakarta.servlet.http.HttpServletRequest
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.transaction.annotation.Transactional
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api/like")
//class LikeController {
//
//    @Autowired
//    private lateinit var likeService: LikeService
//
//    @GetMapping("")
//    fun getLike(httpRequest: HttpServletRequest): List<Like> {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return likeService.read(user = user)
//    }
//
//    @GetMapping("/{id}")
//    fun getLikeById(httpRequest: HttpServletRequest, @PathVariable("id") likeId: Int): Like {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return likeService.read(user = user, id = likeId)
//    }
//
////    @PutMapping("/{id}")
////    @Transactional
////    fun updateLike(
////        httpRequest: HttpServletRequest, @PathVariable("id") likeId: Int, @RequestBody data: Map<String, Any>
////    ): Like {
////        val user = (httpRequest.getAttribute("user") as String).toInt()
////        return likeService.update(user = user, likeId = likeId, data = data)
////    }
//
//    @PostMapping("/{id}")
//    @Transactional
//    fun saveLike(
//        httpRequest: HttpServletRequest, @PathVariable("id") postId: Int, @RequestBody data: Map<String, Any>
//    ): Like {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return likeService.save(user = user, postId = postId, data = data)
//    }
//
//
//    @DeleteMapping("/{id}")
//    @Transactional
//    fun deleteLike(
//        httpRequest: HttpServletRequest, @PathVariable("id") likeId: Int
//    ): Like {
//        val user = (httpRequest.getAttribute("user") as String).toInt()
//        return likeService.delete(user = user, likeId = likeId)
//    }
//
//}