package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(
    private val newsRepository: NewsRepository
    )
{
    suspend fun getSavedNews(): Flow<List<Article>>{
        return newsRepository.getSavedNews()
    }
}