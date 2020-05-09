package com.example.moviesexample.viewmodel

import androidx.lifecycle.*
import com.example.moviesexample.model.Movie
import com.example.moviesexample.model.repository.Repository
import kotlinx.coroutines.launch

class MovieListViewModel(private val moviesRepository: Repository<Movie>): ViewModel() {

    private lateinit var moviesMutableLiveData: MutableLiveData<List<Movie>>
    val moviesLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData

    init {
        refreshMoviesList()
    }

    private fun refreshMoviesList() {
        moviesRepository.fetch()
        viewModelScope.launch {
            moviesMutableLiveData = moviesRepository.fetch().asLiveData() as MutableLiveData<List<Movie>>
        }
    }

    public fun fetchMovies() {
        refreshMoviesList()
    }

}