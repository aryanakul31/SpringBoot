package com.nakul.user.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PostReactionRequestDTO(

    @field:NotNull(message = "Empty Comment")
    @field:NotBlank(message = "Blank Comment")
    var comment: String? = null,

    )