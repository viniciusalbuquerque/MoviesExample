package com.example.moviesexample.viewmodel

import androidx.lifecycle.*
import com.example.moviesexample.model.Movie
import com.example.moviesexample.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(private val moviesRepository: Repository<Movie>): ViewModel() {

    private val moviesMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData

    init {
        refreshMoviesList()
    }

    private fun refreshMoviesList() {
        viewModelScope.launch(Dispatchers.Default) {
            moviesRepository.fetch().collect { moviesMutableLiveData.postValue(it) }
        }
    }

    public fun fetchMovies() {
        refreshMoviesList()
    }

}