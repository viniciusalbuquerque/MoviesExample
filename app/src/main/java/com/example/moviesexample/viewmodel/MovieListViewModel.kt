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

    private val TAG = "MovieListViewModel"

    private val moviesMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData

    private var page = 1

    private fun refreshMoviesList(page: Int) {
        logger.debug(TAG, "refreshMoviesList called")
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(moviesRepository.fetch(page))
        }
    }

    fun fetchNextPage() {
        refreshMoviesList(page++)
    }

    fun searchMovie(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(moviesRepository.search(searchString))
        }
    }

    fun getMovieById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(listOf(moviesRepository.getById(id)))
        }
    }

}