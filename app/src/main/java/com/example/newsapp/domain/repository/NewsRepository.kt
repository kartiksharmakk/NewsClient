package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.APIResponse
import com.example.newsapp.data.model.Article
import com.example.newsfeed.data.util.Resource
import kotlinx.coroutines.flow.Flow

/*
Define abstract functions for al of our use cases and repository implementation.

This repository Interface will serve as a layer between
the data and presentation layers of clean code architecture.

In clean architecture, we do not use any additional frameworks in the domain layer.

FLow used in this instance used to handle data asynchronously.
 */
interface NewsRepository {
    //Return type is APIResponse. This function is ok, it will work for most of the cases. But this time
    //let’s move one step further and consider the state of the api response. Considering state is very useful for
    //error handing and to provide more interactive experience to the user.
    //Usually an API response has 3 states. Loading state, Success state and error state.
    //In their documentations google has provided a generic class that holds a value with its loading status.
    //We can just use that utility class for all our projects.Suspend fun getNewsHeadlines().
    //Return type is APIResponse. This function is ok, it will work for most of the cases. But this time
    //let’s move one step further and consider the state of the api response. Considering state is very useful for
    //error handing and to provide more interactive experience to the user.
    //Usually an API response has 3 states. Loading state, Success state and error state.
    //In their documentations google has provided a generic class that holds a value with its loading status.
    //We can just use that utility class for all our projects.
    /*
    Get method for get use case.
     */
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>

    /*
    Get Search method for search use case.
     */
    suspend fun getSearchedNews(country: String,searchQuery: String,page: Int): Resource<APIResponse>

    /*
    Save method for save news case.
     */
    suspend fun saveNews(article: Article)

    /*
    Delete method for delete use case.
     */
    suspend fun deleteNews(article: Article)

    /*
    Get saved news method for getting the saved news articles.
    Since it will be called from room so we dont need to make it suspend
     */
    fun getSavedNews(): Flow<List<Article>>
}