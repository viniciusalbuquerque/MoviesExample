package com.example.moviesexample.model.repository

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.db.remote.DBService
import com.example.moviesexample.util.AndroidLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class MoviesRepository(private val remoteAPI: DBService<MovieRemote>): Repository<Movie> {

//    override fun fetch() = flow {
//        AndroidLogger.degub("MoviesRepository fetch called!")
//        remoteAPI.get().collect { movieRemoteList ->
//            emit(movieRemoteList.map {
//                Movie(it.id, it.title)
//            })
//        }
//    }.flowOn(Dispatchers.Default)
    override suspend fun fetch() = flow {
        emit(remoteAPI.get().map {
            Movie(it.id, it.title)
        })
    }

    override fun getFavorites() {
    }

    override suspend fun getById(id: String): Flow<Movie> {
        val movieRemote = remoteAPI.getById(id)
        val movie = Movie(movieRemote.id, movieRemote.title)
        return flowOf(movie)

//        remoteAPI.getById(id).collect {
//            emit(Movie(it.id, it.title))
//        }
    }

}