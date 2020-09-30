package com.example.moviesexample.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.Repository
import com.example.moviesexample.model.repository.RepositoryFactory
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.util.Logger
import com.example.moviesexample.view.ui.FavoriteMoviesFragment
import com.example.moviesexample.view.ui.ListMoviesFragment
import com.example.moviesexample.view.ui.MovieListActivity

class ViewModelProvider {

    companion object {

        @Volatile
        private var factory:ViewModelFactory<Movie> ?= null

        private fun <T: ViewModelStoreOwner>get(context: Context, owner: ViewModelStoreOwner, clazz: Class<T>): ViewModel {
            val moviesRepository = RepositoryFactory.getInstance(context)
            val factory = getFactory(moviesRepository)
            val viewModelProvider = ViewModelProvider(owner, factory)
            return when(clazz) {
                ListMoviesFragment::class.java -> viewModelProvider.get(MovieListViewModel::class.java)
                else -> viewModelProvider.get(FavoriteMoviesViewModel::class.java)
            }
        }

        fun createMovieListViewModel(context: Context, owner: ViewModelStoreOwner) =
             get(context, owner, ListMoviesFragment::class.java) as MovieListViewModel

        fun createFavMoviesViewModel(context: Context, owner: ViewModelStoreOwner) =
            get(context, owner, FavoriteMoviesFragment::class.java) as FavoriteMoviesViewModel

        private fun getFactory(repository: Repository<Movie>): ViewModelFactory<Movie> {
            return factory ?: synchronized(this) {
                val fact = ViewModelFactory(repository, AndroidLogger())
                factory = fact
                return fact
            }
        }

    }

}