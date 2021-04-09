package com.example.moviesexample.model.datasource.local.room

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.EntityMapper
import com.example.moviesexample.model.datasource.local.LocalMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieRoomMapper : EntityMapper<LocalMovie, Movie> {
    override suspend fun mapFromEntity(entity: LocalMovie): Movie {
        return withContext(Dispatchers.Default) {
            entity as MovieRoom
            Movie(
                    entity.id,
                    entity.name,
                    entity.imdbRating,
                    entity.favorited,
                    entity.posterUrl
            )
        }
    }

    override suspend fun mapToEntity(domainModel: Movie) = withContext(Dispatchers.Default) {
        MovieRoom(
                domainModel.id,
                domainModel.name,
                domainModel.imdbRating ?: "",
                domainModel.favorited,
                domainModel.posterUrl
        )
    }

    override suspend fun mapFromEntityList(entities: List<LocalMovie>) =
            withContext(Dispatchers.Default) {
                entities.map { mapFromEntity(it) }
            }

    override suspend fun mapToEntityList(domainModels: List<Movie>) =
            withContext(Dispatchers.Default) {
                domainModels.map { mapToEntity(it) }
            }

}