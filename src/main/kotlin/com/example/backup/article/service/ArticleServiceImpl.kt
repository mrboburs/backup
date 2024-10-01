package com.example.backup.article.service

import com.example.backup.article.dto.CreateArticleDto
import com.example.backup.article.dto.UpdateArticleDto
import com.example.backup.article.model.Article


interface ArticleServiceImpl {
    fun findAll(): List<Article>
    fun findById(id: Long): Article?
  fun   save(createArticleDto: CreateArticleDto): CreateArticleDto
    fun deleteById(id: Long)
    fun updateArticleById(
        id: Long, updatedArticle: UpdateArticleDto
    ):Article?
}
