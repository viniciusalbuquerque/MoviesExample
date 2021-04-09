package com.example.moviesexample.model.repository

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.EntityMapper
import com.example.moviesexample.model.datasource.local.LocalDataSource
import com.example.moviesexample.model.datasource.local.LocalMovie
import com.example.moviesexample.model.datasource.remote.MovieRemote
import com.example.moviesexample.model.datasource.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
        private val localDataSource: LocalDataSource<LocalMovie>,
        private val remoteDataSource: RemoteDataSource<MovieRemote>,
        private val localMapper: EntityMapper<LocalMovie, Movie>,
        private val remoteMapper: EntityMapper<MovieRemote, Movie>,
) : Repository<Movie> {

    override suspend fun fetch(page: Int) =
        remoteMapper.mapFromEntityList(remoteDataSource.get(page))

    override suspend fun getById(id: String) = remoteDataSource.getById(id)
        .run {
            remoteMapper.mapFromEntity(this)
        }

    override suspend fun getFavorites() = withContext(Dispatchers.IO) {
        localMapper.mapFromEntityList(localDataSource.getAll()).filter { it.favorited }
    }

    override suspend fun search(searchString: String) =
        remoteMapper.mapFromEntityList(remoteDataSource.search(searchString))

    override suspend fun addToFavorite(movie: Movie) {
        movie.favorited = true
        localDataSource.add(localMapper.mapToEntity(movie))
    }

    override suspend fun removeFromFavorites(movie: Movie) =
            localDataSource.delete(localMapper.mapToEntity(movie))

    override suspend fun getFavById(id: String): Movie {
        localDataSource.getById(id)?.let {
            return localMapper.mapFromEntity(it)
        }
        return Movie(id, "", null, false)
    }

}