package com.marlhex.moviebrowsermvvm

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class ApiResponse(
    val results: List<Movie>
)

data class Movie(
    val id: String = UUID.randomUUID().toString(),
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String
) {
    val posterUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$posterPath"

    val backdropUrl: String
        get() = "https://image.tmdb.org/t/p/w500/$backdropPath"
}