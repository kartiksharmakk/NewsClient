package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsfeed.data.util.Resource

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {
        suspend fun execute(country:String,searchQuery: String,page:Int): Resource<APIResponse>{
            return newsRepository.getSearchedNews(country,searchQuery,page)
        }
}