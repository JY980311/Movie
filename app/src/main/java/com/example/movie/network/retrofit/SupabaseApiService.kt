package com.example.movie.network.retrofit

import com.example.movie.network.model.post.PostDataItem
import com.example.movie.network.model.post.SendPostData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface SupabaseApiService {
    @GET("Post")
    suspend fun getAllPost(
        @Query("select") select: String = "*",
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkcG9hZHFuYm56bW15Z25zYWR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTgzNDYzOTgsImV4cCI6MjAzMzkyMjM5OH0.slFtE--CP7YabhnPxsFhIhCnKdeB8aZZdgjMKJOzEEQ",
    ) : List<PostDataItem>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkcG9hZHFuYm56bW15Z25zYWR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTgzNDYzOTgsImV4cCI6MjAzMzkyMjM5OH0.slFtE--CP7YabhnPxsFhIhCnKdeB8aZZdgjMKJOzEEQ",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkcG9hZHFuYm56bW15Z25zYWR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTgzNDYzOTgsImV4cCI6MjAzMzkyMjM5OH0.slFtE--CP7YabhnPxsFhIhCnKdeB8aZZdgjMKJOzEEQ",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("Post")
    suspend fun createPost(
        @Query("apikey") apikey: String = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpkcG9hZHFuYm56bW15Z25zYWR4Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTgzNDYzOTgsImV4cCI6MjAzMzkyMjM5OH0.slFtE--CP7YabhnPxsFhIhCnKdeB8aZZdgjMKJOzEEQ",
        @Body sendPostData: SendPostData
    ) : Response<List<SendPostData>>
}