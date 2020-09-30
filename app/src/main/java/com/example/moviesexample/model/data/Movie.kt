package com.example.moviesexample.model.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val id: String,
    val name: String,
    val imdbRating: String?,
    var favorited: Boolean = false,
    var posterUrl: String = "")

