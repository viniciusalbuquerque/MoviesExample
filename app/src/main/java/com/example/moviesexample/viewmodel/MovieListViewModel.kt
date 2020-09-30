package com.example.moviesexample.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieListViewModel(private val moviesRepository: Repository<Movie>,
                         private val logger: Logger) : ViewModel() {

    private val moviesMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData

    private fun refreshMoviesList() {
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(moviesRepository.fetch())
        }
    }

    fun fetchMovies() {
        refreshMoviesList()
    }

    fun searchMovie(searchString: String = "galaxy") { // TODO: Remove "galaxy"
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(moviesRepository.search(searchString))
        }
    }

    fun getMovieById(id: String = "tt3896198") { // TODO: Remove default id
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(listOf(moviesRepository.getById(id)))
        }
    }

}