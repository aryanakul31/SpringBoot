//package com.nakul.user.entities
//
//import jakarta.persistence.*
//
//
//@Entity
//@Table
//data class Comment(
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
//    val id: Int = 0,
//
//    var text: String,
//
////    @ManyToOne
////    @JoinColumn(name = "user_id")
//    var user: Int,
//
////    @ManyToOne
////    @JoinColumn(name = "post_id")
//    var post: Post,
//
////    @ManyToOne
////    @JoinColumn(name = "comment_id")
//    var commentId: Int? = null,
//
//    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
//    @JoinColumn(name = "parentId")
//    val childComments: Set<Comment>? = null
//) {
//    override fun toString(): String {
//        return "Comment(id=$id, text='$text', postId=$id, user=$user)"
//    }
//}
