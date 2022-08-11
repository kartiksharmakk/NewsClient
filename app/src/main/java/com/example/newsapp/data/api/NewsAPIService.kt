package com.example.newsapp.data.api

import com.example.newsapp.BuildConfig
import com.example.newsapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country:String,
        @Query("page")
        page:Int,
        @Query("apiKey")
        apiKey:String=BuildConfig.API_KEY
    ):Response<APIResponse>
}