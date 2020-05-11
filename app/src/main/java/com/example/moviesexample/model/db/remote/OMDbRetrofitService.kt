package com.example.moviesexample.model.db.remote

import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.data.OMDbSearchResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface OMDbRetrofitService {

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getMovies(@Query("apiKey") apiKey: String,
                          @Query("page") page: String = "1",
                          @Query("s") temporarySearch: String = "car")
            :Response<OMDbSearchResponse>

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun getMovieById(@Query("apiKey") apiKey: String, @Query("i") id:String): Response<MovieRemote>

    @Headers("Accept: application/json")
    @GET("/")
    suspend fun searchMovies(@Query("apiKey") apiKey: String,
                          @Query("page") page: String = "1",
                          @Query("s") searchString: String)
            :Response<OMDbSearchResponse>
}