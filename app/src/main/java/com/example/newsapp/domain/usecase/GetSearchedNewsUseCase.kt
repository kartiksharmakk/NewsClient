package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsfeed.data.util.Resource

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {
        suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse>{
            return newsRepository.getSearchedNews(searchQuery)
        }
}