package com.example.moviesexample.usecases

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository

class SearchMovieUseCase(private val moviesRepository: Repository<Movie>):
        UseCase<String, List<Movie>> {
    override suspend fun execute(param: String) = moviesRepository.search(param)
}