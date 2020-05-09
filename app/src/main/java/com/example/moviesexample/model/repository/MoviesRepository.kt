package com.example.moviesexample.model.repository

import com.example.moviesexample.model.Movie
import kotlinx.coroutines.flow.flow

class MoviesRepository: Repository<Movie> {

    override fun fetch() = flow {
        emit(listOf(Movie("Movie1"), Movie("Movie2")))
    }

}