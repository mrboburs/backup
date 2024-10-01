package com.example.backup.article.repository

import com.example.backup.article.model.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ArticleRepository : JpaRepository<Article, Long>
