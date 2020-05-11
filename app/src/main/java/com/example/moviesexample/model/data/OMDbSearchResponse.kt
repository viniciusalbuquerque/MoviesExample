package com.example.moviesexample.model.data

import com.google.gson.annotations.SerializedName

data class OMDbSearchResponse(
    @SerializedName("Search")
    val movieRemoteList: List<MovieRemote>
)
