package com.example.moviesexample.model.db.remote

import kotlinx.coroutines.flow.Flow

interface DBService<T> {
    suspend fun get() : List<T>
    suspend fun getById(id: String): T
}