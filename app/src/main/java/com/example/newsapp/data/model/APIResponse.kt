package com.example.newsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class APIResponse(
    //@SerializedName("articles")
    val articles: List<Article>,
    //@SerializedName("status")
    val status: String?,
    //@SerializedName("totalResults")
    val totalResults: Int?
):Parcelable