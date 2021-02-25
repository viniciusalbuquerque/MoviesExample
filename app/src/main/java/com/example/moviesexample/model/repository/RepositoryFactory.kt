package com.example.moviesexample.model.repository

import android.content.Context
import com.example.moviesexample.R
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.db.local.MoviesRoomDatabase
import com.example.moviesexample.model.db.remote.OMDbAPI
import com.example.moviesexample.util.AndroidLogger

class RepositoryFactory {

    companion object {

        @Volatile
        private var repository: Repository<Movie>? = null

        fun getInstance(context: Context): Repository<Movie> {
            return repository ?: synchronized(this) {
                val movieDatabase = MoviesRoomDatabase.getDatabase(context)
                val repo = MoviesRepository(
                    movieDatabase.moviesDao(),
                    OMDbAPI(context.getString(R.string.apiKey)))
                repository = repo
                return repo
            }
        }
    }

}