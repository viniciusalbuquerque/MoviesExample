package com.example.moviesexample.model.db.remote

import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.db.remote.DBService
import com.example.moviesexample.util.AndroidLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OMDbAPI(apiKey: String):
    DBService<MovieRemote> {

    private val URL = "http://www.omdbapi.com/?apikey=$apiKey&s=car"
    //val URL = "http://www.omdbapi.com/"
    val retrofitService: OMDbRetrofitService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = retrofit.create(OMDbRetrofitService::class.java)
    }

    override suspend fun get() = retrofitService.getMovies()
//        retrofitService.getMovies().collect {
//            emit(it)
//        }
//        AndroidLogger.degub("OMDbAPI get called")
//        emit(listOf(
//            MovieRemote("1","Movie1"),
//            MovieRemote("2", "Movie2")
//        ))
//    }.flowOn(Dispatchers.Default)

    override suspend fun getById(id: String) = retrofitService.getMovieById(id)
}