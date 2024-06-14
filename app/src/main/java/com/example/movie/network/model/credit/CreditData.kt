package com.example.movie.network.model.credit

data class CreditData(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)