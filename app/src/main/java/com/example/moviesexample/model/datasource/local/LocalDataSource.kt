package com.example.moviesexample.model.datasource.local

interface LocalDataSource<T> {

    suspend fun getAll(): List<@JvmSuppressWildcards T>
    suspend fun add(item: T)
    suspend fun add(item: List<@JvmSuppressWildcards T>)
    suspend fun delete(item: T)
    suspend fun getById(id: String): T?

}