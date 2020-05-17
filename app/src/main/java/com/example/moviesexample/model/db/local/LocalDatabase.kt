package com.example.moviesexample.model.db.local

import kotlinx.coroutines.flow.Flow

interface LocalDatabase<T> {

    fun getAll(): Flow<List<T>>

}