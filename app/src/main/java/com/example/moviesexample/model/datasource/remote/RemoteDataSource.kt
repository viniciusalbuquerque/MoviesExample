package com.example.moviesexample.model.datasource.remote

interface RemoteDataSource<T> {
    suspend fun get(page: Int = 1) : List<T>
    suspend fun search(searchString: String, page: Int = 1) : List<T>
    suspend fun getById(id: String): T
}