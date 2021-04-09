package com.example.moviesexample.usecases

import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository
import com.example.moviesexample.util.AndroidLogger

class FetchMoviesUseCase(private val moviesRepository: Repository<Movie>) : UseCase<Int, List<Movie>> {
    override suspend fun execute(page: Int) = moviesRepository.fetch(page)
}