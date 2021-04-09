package com.example.moviesexample.viewmodel.listmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.usecases.UseCase
import com.example.moviesexample.util.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(private val fetchMoviesUseCase: UseCase<Int, List<Movie>>,
                         private val getMovieByIdUseCase: UseCase<String, Movie>,
                         private val searchMovieUseCase: UseCase<String, List<Movie>>,
                         private val addFavoriteUseCase: UseCase<Movie, Unit>,
                         private val removeFavoriteUseCase: UseCase<Movie, Unit>,
                         private val logger: Logger) : ViewModel() {

    private val TAG = "MovieListViewModel"

    private val moviesMutableLiveData = MutableLiveData<List<Movie>>()
    val moviesLiveData: LiveData<List<Movie>>
        get() = moviesMutableLiveData

    private var page = 1

    private fun refreshMoviesList(page: Int) {
        logger.debug(TAG, "refreshMoviesList called")
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(fetchMoviesUseCase.execute(page))
        }
    }

    fun fetchNextPage() {
        refreshMoviesList(page++)
    }

    fun searchMovie(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(searchMovieUseCase.execute(searchString))
        }
    }

    fun getMovieById(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            moviesMutableLiveData.postValue(listOf(getMovieByIdUseCase.execute(id)))
        }
    }

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
/*            moviesRepository.getFavById(movie.id)?.let {
                if (it.favorited) {
                    removeFromFavorite(movie)
                } else {
                    addtoFavorite(movie)
                }
            }
  */      }
    }

    fun addtoFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteUseCase.execute(movie)
        }
    }

    fun removeFromFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFavoriteUseCase.execute(movie)
        }
    }

}