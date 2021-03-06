package com.example.moviesexample.model.db.local

import androidx.room.Dao
import androidx.room.Query
import com.example.moviesexample.model.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesRoomDao : LocalDatabase<Movie> {

    @Query("Select * FROM movie")
    override suspend fun getAll(): List<Movie>

}