package com.example.newsapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Source(
    //@SerializedName("id")
    val id: String?,
    //@SerializedName("name")
    val name: String?
):Parcelable