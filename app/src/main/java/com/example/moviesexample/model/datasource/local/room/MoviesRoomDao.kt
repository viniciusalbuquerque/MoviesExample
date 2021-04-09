package com.example.moviesexample.model.datasource.local.room

import androidx.room.*
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.local.LocalDataSource
import com.example.moviesexample.model.datasource.local.LocalMovie

@Dao
interface MoviesRoomDao : LocalDataSource<MovieRoom> {

    @Query("Select * FROM movieroom")
    override suspend fun getAll(): List<MovieRoom>

    @Query("SELECT * FROM movieroom WHERE id = :id")
    override suspend fun getById(id: String): MovieRoom?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun add(movie: MovieRoom)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun add(item: List<MovieRoom>)

    @Delete
    override suspend fun delete(item: MovieRoom)

}