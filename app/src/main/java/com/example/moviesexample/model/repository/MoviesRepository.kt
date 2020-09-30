package com.example.moviesexample.model.repository

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.db.local.LocalDatabase
import com.example.moviesexample.model.db.local.MoviesRoomDao
import com.example.moviesexample.model.db.remote.DBService
import com.example.moviesexample.util.AndroidLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepository(private val localDB: MoviesRoomDao, private val remoteAPI: DBService<MovieRemote>): Repository<Movie> {

    override suspend fun fetch() = flow {
        emit(remoteAPI.get().map {
            AndroidLogger.degub(it.toString())
            Movie(it.id, it.title, it.imdbRating)
        })
    }

    override suspend fun getById(id: String) = flow {
        val movieRemote = remoteAPI.getById(id)
        emit(Movie(movieRemote.id, movieRemote.title, movieRemote.imdbRating))
    }

    override suspend fun getFavorites() = localDB.getAll()

    override suspend fun searchMovie(searchString: String) = flow {
        emit(remoteAPI.search(searchString).map {
            Movie(it.id, it.title, it.imdbRating)
        })
    }

}