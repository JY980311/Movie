package com.example.movie.network.model.post

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SendPostData(
    @Json(name = "created_at")
    val created_at: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "movie")
    val movie: String?,
)