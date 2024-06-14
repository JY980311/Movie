package com.example.movie.network.model.movie

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieData(

    @Json(name = "dates")
    val dates: Dates = Dates(),

    @Json(name = "page")
    val page: Int = 0,

    @Json(name = "results")
    val results: List<Result> = emptyList(),

    @Json(name = "total_pages")
    val total_pages: Int = 0,

    @Json(name = "total_results")
    val total_results: Int = 0,
)