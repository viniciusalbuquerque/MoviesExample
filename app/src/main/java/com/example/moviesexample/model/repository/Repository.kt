package com.example.moviesexample.model.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun fetch(): Flow<List<T>>
    fun getFavorites()
    suspend fun getById(id: String): Flow<T>
}