package com.example.backup.article.controller

import com.example.backup.article.dto.CreateArticleDto
import com.example.backup.article.dto.UpdateArticleDto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RequestMapping("/articles")
interface ArticleControllerImpl {

    @PostMapping
    fun createArticle(
        @Valid @RequestBody article: CreateArticleDto,
    ): ResponseEntity<Any>


    @GetMapping
    fun getAllArticles(
    ): ResponseEntity<Any>


    @GetMapping("/{id}")
    fun getArticleById(
        @PathVariable id: Long
    ): ResponseEntity<Any>


    @PutMapping("/{id}")
    fun updateArticleById(
        @PathVariable id: Long,
        @Valid @RequestBody updatedArticle: UpdateArticleDto
    ): ResponseEntity<Any>


    @DeleteMapping("/{id}")
    fun deleteArticle(
        @PathVariable  id: Long
    ): ResponseEntity<Void>
}