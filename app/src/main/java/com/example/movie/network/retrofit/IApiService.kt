package com.example.movie.network.retrofit

import com.example.movie.network.model.credit.CreditData
import com.example.movie.network.model.detail.DetailMovieData
import com.example.movie.network.model.movie.MovieData
import com.example.movie.network.model.image.ImageData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IApiService {
    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("language") language: String = "ko-KR",
        @Query("api_key") apiKey: String = "2d479c2e019799da99365eda854d80fe", //TODO: 추후에 API Key를 숨기기 위해 보안 처리 필요
        @Query("region") region: String = "KR",
        @Query("page") page: Int,
    ) : MovieData

    @GET("{movie_id}/images")
    suspend fun getImages(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "2d479c2e019799da99365eda854d80fe",
        //@Query("include_image_language") includeImageLanguage: String = "en"
    ) : ImageData

    @GET("{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "2d479c2e019799da99365eda854d80fe",
        @Query("language") language: String = "ko-KR"
    ) : CreditData

    @GET("{movie_id}")
    suspend fun getDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "2d479c2e019799da99365eda854d80fe",
        @Query("language") language: String = "ko-KR"
    ) : DetailMovieData
}