package com.example.moviesexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(private val moviesRepository: Repository<Movie>): ViewModel() {

    private val favMoviesMutableLiveData = MutableLiveData<List<Movie>>()
    val favMoviesLiveData: LiveData<List<Movie>>
        get() = favMoviesMutableLiveData

    fun refreshFavoriteMovies() {
        viewModelScope.launch(Dispatchers.Default) {
            moviesRepository.getFavorites().collect { favMoviesMutableLiveData.postValue(it) }
        }
    }

}