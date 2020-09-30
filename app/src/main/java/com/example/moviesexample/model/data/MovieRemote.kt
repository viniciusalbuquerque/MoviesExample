package com.example.moviesexample.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieRemote(
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
    val posterUrl: String)
