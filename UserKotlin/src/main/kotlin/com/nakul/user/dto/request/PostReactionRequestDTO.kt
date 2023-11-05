package com.nakul.user.dto.request

data class PostReactionRequestDTO(
    val isLiked: Boolean = false,
    val comment: String? = null,
)