package com.example.backup.service

import com.example.backup.entity.Article
import com.example.backup.repository.ArticleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleService @Autowired constructor(private val articleRepository: ArticleRepository) {

    fun findAll(): List<Article> = articleRepository.findAll()

    fun findById(id: Long): Article? = articleRepository.findById(id).orElse(null)

    fun save(product: Article): Article = articleRepository.save(product)

    fun deleteById(id: Long) = articleRepository.deleteById(id)
}