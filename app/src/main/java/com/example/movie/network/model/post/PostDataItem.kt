package com.example.movie.network.model.post

data class PostDataItem(
    val content: String,
    val created_at: String,
    val id: Int,
    val movie: String,
    val title: String
)