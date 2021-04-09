package com.example.moviesexample.model.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviesexample.model.datasource.local.LocalMovie

@Entity
data class MovieRoom(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val imdbRating: String = "",
    var favorited: Boolean = true,
    var posterUrl: String = "") : LocalMovie