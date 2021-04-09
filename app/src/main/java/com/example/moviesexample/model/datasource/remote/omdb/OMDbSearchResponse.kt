package com.example.moviesexample.model.datasource.remote.omdb

import com.google.gson.annotations.SerializedName

data class OMDbSearchResponse(
    @SerializedName("Search")
    val movieRemoteList: List<MovieOMDb>
)
