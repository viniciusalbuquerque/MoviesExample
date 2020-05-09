package com.example.moviesexample.model.repository

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    public fun fetch(): Flow<List<T>>
}