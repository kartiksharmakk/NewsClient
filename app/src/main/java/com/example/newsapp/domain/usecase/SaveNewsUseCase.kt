package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository

class SaveNewsUseCase(
    private val newsRepository: NewsRepository) {
    suspend fun saveNews(article: Article){
        return newsRepository.saveNews(article)
    }
}