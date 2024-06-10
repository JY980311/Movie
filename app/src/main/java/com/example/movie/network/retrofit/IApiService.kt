package com.example.movie.network.retrofit

import com.example.movie.network.model.MovieData
import retrofit2.http.GET
import retrofit2.http.Query

interface IApiService {
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String = "ko-KR",
        @Query("api_key") apiKey: String = "2d479c2e019799da99365eda854d80fe", //TODO: 추후에 API Key를 숨기기 위해 보안 처리 필요
        @Query("region") region: String = "KR",
        @Query("page") page: Int = 1,
    ) : MovieData
}