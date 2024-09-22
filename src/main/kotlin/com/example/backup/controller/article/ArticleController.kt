package com.example.backup.controller.article

import com.example.backup.entity.Article
import com.example.backup.service.ArticleService

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/articles")
class ArticleController @Autowired constructor(private val articleService: ArticleService) {

    // Create a new article
    @PostMapping
    fun createArticle(@Valid @RequestBody article: Article): ResponseEntity<Any> {
        val createdArticle = articleService.save(article)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArticle)
    }

    // Get all articles
    @GetMapping
    fun getAllArticles(): ResponseEntity<Any> {
        val articles = articleService.findAll()
        return ResponseEntity.ok(articles)
    }

    // Get a article by ID
    @GetMapping("/{id}")
    fun getArticleById(@PathVariable id: Long): ResponseEntity<Any> {
        val article = articleService.findById(id)
        return if (article != null) {
            ResponseEntity.ok(article)
        } else {
            val errorBody = mapOf("error" to "Article with ID $id not found")
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody)
        }
    }

    // Update a article by ID
    @PutMapping("/{id}")
    fun updateArticle(
        @PathVariable id: Long,
        @Valid @RequestBody updatedArticle: Article
    ): ResponseEntity<Any> {
        val existingArticle = articleService.findById(id)
        return if (existingArticle != null) {
            val articleToUpdate = existingArticle.copy(title = updatedArticle.title, content = updatedArticle.content)
            ResponseEntity.ok(articleService.save(articleToUpdate))
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Delete a article by ID
    @DeleteMapping("/{id}")
    fun deleteArticle(@PathVariable id: Long): ResponseEntity<Void> {
        val article = articleService.findById(id)
        return if (article != null) {
            articleService.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
