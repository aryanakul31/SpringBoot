package com.nakul.user.dto.response


data class PostResponseDTO(
    var postId: Int = 0,
    var title: String? = "",
    var description: String = "",
//    var likes : List<Like>
)