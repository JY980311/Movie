package com.example.movie.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @Json(name = "adult")
    val adult: Boolean = false,

    @Json(name = "backdrop_path")
    val backdrop_path: String = "",

    @Json(name = "genre_ids")
    val genre_ids: List<Int> = emptyList(),

    @Json(name = "id")
    val id: Int = 0,

    @Json(name = "original_language")
    val original_language: String = "",

    @Json(name = "original_title")
    val original_title: String = "",

    @Json(name = "overview")
    val overview: String = "",

    @Json(name = "popularity")
    val popularity: Double = 0.0,

    @Json(name = "poster_path")
    val poster_path: String = "",

    @Json(name = "release_date")
    val release_date: String = "",

    @Json(name = "title")
    val title: String = "",

    @Json(name = "video")
    val video: Boolean = false,

    @Json(name = "vote_average")
    val vote_average: Double = 0.0,

    @Json(name = "vote_count")
    val vote_count: Int = 0
)