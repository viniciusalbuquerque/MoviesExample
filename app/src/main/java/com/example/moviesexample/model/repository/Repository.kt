package com.example.moviesexample.model.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun fetch(): Flow<List<T>>
    suspend fun getFavorites(): Flow<List<T>>
    suspend fun getById(id: String): Flow<T>
    suspend fun searchMovie(searchString: String): Flow<List<T>>
}