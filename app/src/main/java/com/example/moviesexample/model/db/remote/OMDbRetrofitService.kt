package com.example.moviesexample.model.db.remote

import com.example.moviesexample.model.data.MovieRemote
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

interface OMDbRetrofitService {

    @GET("/")
    fun getMovies():List<MovieRemote>

    @GET("&i={id}")
    fun getMovieById(@Path("id") id:String): MovieRemote
}