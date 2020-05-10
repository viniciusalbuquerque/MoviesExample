package com.example.moviesexample.model.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieRemote(
    @SerializedName("imdbID")
    @Expose
    val _id: String,
    @SerializedName("Title")
    @Expose
    val title: String)
    : Data(_id)