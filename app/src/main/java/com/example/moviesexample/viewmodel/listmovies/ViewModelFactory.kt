package com.example.moviesexample.viewmodel.listmovies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.usecases.UseCase
import com.example.moviesexample.util.Logger

class ViewModelFactory(
    private val fetchMoviesUseCase: UseCase<Int, List<Movie>>,
    private val getMovieByIdUseCase: UseCase<String, Movie>,
    private val searchMovieUseCase: UseCase<String, List<Movie>>,
    private val addFavoriteUseCase: UseCase<Movie, Unit>,
    private val removeFavoriteUseCase: UseCase<Movie, Unit>,
    private val logger: Logger,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            UseCase::class.java,
            UseCase::class.java,
            UseCase::class.java,
            UseCase::class.java,
            UseCase::class.java,
            Logger::class.java
        )
            .newInstance(
                fetchMoviesUseCase,
                getMovieByIdUseCase,
                searchMovieUseCase,
                addFavoriteUseCase,
                removeFavoriteUseCase,
                logger
            )
    }
}