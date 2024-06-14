package com.example.movie.network.retrofit

import com.example.movie.network.model.post.PostData
import retrofit2.http.GET
import retrofit2.http.Query

interface supabaseApiService {
    @GET("Post")
    suspend fun getAllPost(
        @Query("select") select: String = "*",
        @Query("apikey") order: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkcG9hZHFuYm56bW15Z25zYWR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTgzNDYzOTgsImV4cCI6MjAzMzkyMjM5OH0.slFtE--CP7YabhnPxsFhIhCnKdeB8aZZdgjMKJOzEEQ",
    ) : PostData
}