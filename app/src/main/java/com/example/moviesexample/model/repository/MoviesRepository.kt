package com.example.moviesexample.model.repository

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.db.local.LocalDatabase
import com.example.moviesexample.model.db.remote.DBService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val localDB: LocalDatabase<Movie>,
    private val remoteAPI: DBService<MovieRemote>,
) : Repository<Movie> {

    override suspend fun fetch(page: Int) = remoteAPI.get(page)
        .map {
            it.toMovie()
        }

    override suspend fun getById(id: String) = remoteAPI.getById(id)
        .run {
            this.toMovie()
        }

    override suspend fun getFavorites() = withContext(Dispatchers.IO) {
        localDB.getAll()
    }

    override suspend fun search(searchString: String) = remoteAPI.search(searchString)
        .map {
            it.toMovie()
        }

}