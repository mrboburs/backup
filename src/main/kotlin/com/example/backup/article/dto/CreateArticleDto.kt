package com.example.backup.article.dto

import jakarta.validation.constraints.NotBlank

data class CreateArticleDto(
    @field:NotBlank(message = "Title must not be blank")
    val title: String,

    @field:NotBlank(message = "Content must not be blank")
    val content: String
)
