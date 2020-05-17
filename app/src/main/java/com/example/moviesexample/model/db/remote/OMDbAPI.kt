package com.example.moviesexample.model.db.remote

import com.example.moviesexample.model.data.MovieRemote
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class OMDbAPI(private val apiKey: String):
    DBService<MovieRemote> {

    private val url = "http://www.omdbapi.com/"
    private val retrofitService: OMDbRetrofitService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = retrofit.create(OMDbRetrofitService::class.java)
    }

    override suspend fun get(page: Int): List<MovieRemote> =
         retrofitService.getMovies(apiKey, page.toString()).body()?.movieRemoteList ?: listOf()

    override suspend fun getById(id: String) = retrofitService.getMovieById(apiKey, id).body()
            ?: throw Exception("Movie with id = $id not found")

    override suspend fun search(searchString: String, page: Int) =
        retrofitService.searchMovies(apiKey, page.toString(), searchString).body()?.movieRemoteList
            ?: listOf()
}