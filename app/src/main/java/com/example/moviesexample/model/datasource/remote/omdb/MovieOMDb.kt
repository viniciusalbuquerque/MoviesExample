package com.example.moviesexample.model.datasource.remote.omdb

import com.example.moviesexample.model.datasource.remote.MovieRemote
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieOMDb(
    @SerializedName("imdbID")
    @Expose
    val id: String,
    @SerializedName("Title")
    @Expose
    val title: String,
    @SerializedName("imdbRating")
    @Expose
    val imdbRating: String,
    @SerializedName("Poster")
    @Expose
    val posterUrl: String): MovieRemote
