package com.example.newsapp.data.repository.dataSourceImpl

import com.example.newsapp.data.api.NewsAPIService
import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
     private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource{
    override suspend fun getTopHeadlines(country: String,pages: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadlines(country,pages)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadlines(country,searchQuery, page)
    }

}