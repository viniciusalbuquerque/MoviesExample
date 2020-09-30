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
        logger.debug("refreshMoviesList called!")
        viewModelScope.launch(Dispatchers.Default) {
            moviesRepository.fetch().collect { moviesMutableLiveData.postValue(it) }
        }
    }

    fun fetchMovies() {
        refreshMoviesList()
    }

    fun searchMovie(searchString: String) {
        viewModelScope.launch(Dispatchers.Default) {
            moviesRepository.searchMovie("galaxy").collect { moviesMutableLiveData.postValue(it) }
        }
    }

    fun getMovieById(id: String) {
        viewModelScope.launch {
            moviesRepository.getById("tt3896198").collect {
                moviesMutableLiveData.postValue(listOf(it))
            }
        }
    }

}