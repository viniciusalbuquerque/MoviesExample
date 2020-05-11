package com.example.moviesexample.model.db.remote

interface DBService<T> {
    suspend fun get(page: Int = 1) : List<T>
    suspend fun search(searchString: String, page: Int = 1) : List<T>
    suspend fun getById(id: String): T
}