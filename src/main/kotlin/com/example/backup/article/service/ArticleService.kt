package com.example.backup.article.service

import com.example.backup.article.dto.CreateArticleDto
import com.example.backup.article.dto.UpdateArticleDto
import com.example.backup.article.model.Article
import com.example.backup.article.repository.ArticleRepository
import org.springframework.stereotype.Service


@Service
class ArticleService(
    private val articleRepository: ArticleRepository
) : ArticleServiceImpl {

    override fun findAll(): List<Article> = articleRepository.findAll()

    override fun findById(id: Long): Article? = articleRepository.findById(id).orElse(null)
  override  fun updateArticleById(
      id: Long, updatedArticle: UpdateArticleDto
  ): Article? {
        val existingArticle = findById(id)
        return if (existingArticle != null) {
            val articleToUpdate = existingArticle.copy(
                title = updatedArticle.title,
                content = updatedArticle.content
            )
            articleRepository.save(articleToUpdate)
        } else {
            null
        }
    }

    override fun save(createArticleDto: CreateArticleDto): CreateArticleDto {
        val article = Article(
            title = createArticleDto.title,
            content = createArticleDto.content
        )
        val savedArticle = articleRepository.save(article)
        return CreateArticleDto(
            title = savedArticle.title,
            content = savedArticle.content
        )
    }

    override fun deleteById(id: Long) = articleRepository.deleteById(id)
}

