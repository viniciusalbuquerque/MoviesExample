package com.example.moviesexample.model.repository

import android.content.Context
import com.example.moviesexample.R
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.local.LocalDataSource
import com.example.moviesexample.model.datasource.local.LocalMovie
import com.example.moviesexample.model.datasource.local.room.MovieRoomMapper
import com.example.moviesexample.model.datasource.local.room.MoviesRoomDatabase
import com.example.moviesexample.model.datasource.remote.omdb.MovieOMDbMapper
import com.example.moviesexample.model.datasource.remote.omdb.OMDbAPI

class RepositoryFactory {

    companion object {

        @Volatile
        private var repository: Repository<Movie>? = null

        fun getInstance(context: Context): Repository<Movie> {
            return repository ?: synchronized(this) {
                val movieDatabase = MoviesRoomDatabase.getDatabase(context)
                val repo = MoviesRepository(
                    movieDatabase.moviesDao() as LocalDataSource<LocalMovie>,
                    OMDbAPI(context.getString(R.string.apiKey)),
                    MovieRoomMapper(),
                    MovieOMDbMapper())
                repository = repo
                return repo
            }
        }
    }

}