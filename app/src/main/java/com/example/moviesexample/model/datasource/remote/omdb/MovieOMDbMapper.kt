package com.example.moviesexample.model.datasource.remote.omdb

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.EntityMapper
import com.example.moviesexample.model.datasource.remote.MovieRemote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieOMDbMapper : EntityMapper<MovieRemote, Movie> {
    override suspend fun mapFromEntity(entity: MovieRemote): Movie {
        return withContext(Dispatchers.Default) {
            entity as MovieOMDb
            Movie(
                    entity.id,
                    entity.title,
                    entity.imdbRating,
                    posterUrl = entity.posterUrl
            )
        }
    }

    override suspend fun mapToEntity(domainModel: Movie): MovieOMDb {
        return withContext(Dispatchers.Default) {
            val imdbRating = domainModel.imdbRating ?: ""
            MovieOMDb(
                    domainModel.id,
                    domainModel.name,
                    imdbRating,
                    domainModel.posterUrl
            )
        }
    }

    override suspend fun mapFromEntityList(entities: List<MovieRemote>) =
            withContext(Dispatchers.Default) {
                entities.map { mapFromEntity(it) }
            }

    override suspend fun mapToEntityList(domainModels: List<Movie>) =
            withContext(Dispatchers.Default) {
                domainModels.map { mapToEntity(it) }
            }

}