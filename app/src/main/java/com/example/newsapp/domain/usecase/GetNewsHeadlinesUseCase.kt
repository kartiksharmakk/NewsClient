package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsfeed.data.util.Resource
import retrofit2.Response

class GetNewsHeadlinesUseCase(private var newsRepository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }

}