package com.example.moviesexample.model.data

data class Movie(
    val id: String,
    val name: String,
    val imdbRating: String?,
    var favorited: Boolean = false,
    var posterUrl: String = ""
)
