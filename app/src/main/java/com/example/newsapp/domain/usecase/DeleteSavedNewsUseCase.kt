package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(
    private val newsRepository: NewsRepository
) {
        suspend fun deleteNews(article: Article){
            return newsRepository.deleteNews(article)
        }
}