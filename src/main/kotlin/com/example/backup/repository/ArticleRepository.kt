package com.example.backup.repository

import com.example.backup.entity.Article

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ArticleRepository : JpaRepository<Article, Long>
