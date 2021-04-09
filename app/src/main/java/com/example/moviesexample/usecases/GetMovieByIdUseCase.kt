package com.example.moviesexample.usecases

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository

class GetMovieByIdUseCase(private val moviesRepository: Repository<Movie>): UseCase<String, Movie> {
    override suspend fun execute(id: String) = moviesRepository.getById(id)
}