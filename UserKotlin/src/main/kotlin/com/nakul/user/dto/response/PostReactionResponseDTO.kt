package com.nakul.user.dto.response

data class PostReactionResponseDTO(
    var postReactionId: Int = 0,

    var user: UserResponseDTO? = null,
    var postId: Int = 0,
    var comment: String? = null,
    var isLiked: Boolean = false,
)
