package com.example.moviesexample.usecases

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository

class RemoveFavoriteUseCase(private val movieRepository: Repository<Movie>): UseCase<Movie, Unit> {
    override suspend fun execute(movie: Movie) {
        movieRepository.removeFromFavorites(movie)
    }
}