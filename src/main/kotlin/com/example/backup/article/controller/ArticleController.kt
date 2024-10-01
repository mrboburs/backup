package com.example.backup.article.controller

import com.example.backup.article.dto.CreateArticleDto
import com.example.backup.article.dto.UpdateArticleDto
import com.example.backup.article.service.ArticleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class ArticleController(
    private val articleService: ArticleService
            ) : ArticleControllerImpl {

    // Create a new article
    override fun createArticle(
        article: CreateArticleDto,
    ): ResponseEntity<Any> {
        val createdArticle = articleService.save(article)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle)
    }


    // Get all articles
    override fun getAllArticles(
    ): ResponseEntity<Any> {
        val articles = articleService.findAll()
        return ResponseEntity.ok(articles)
    }


    // Get an article by ID
    override fun getArticleById(
        id: Long
    ): ResponseEntity<Any> {
        val article = articleService.findById(id)
        return if (article != null) {
            ResponseEntity.ok(article)
        } else {
            val errorBody = mapOf("error" to "Article with ID $id not found")
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
        }
    }



//     Update an article by ID
    override fun updateArticleById(
         id: Long,
         updatedArticle: UpdateArticleDto
    ): ResponseEntity<Any> {
        return articleService.updateArticleById(id, updatedArticle)?.let {
            ResponseEntity.ok(it) // Return the updated article
        } ?: ResponseEntity.notFound().build() // Return 404 if not found
    }



    // Delete an article by ID
    override fun deleteArticle(
        id: Long
    ): ResponseEntity<Void> {
        val article = articleService.findById(id)
        return if (article != null) {
            articleService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
