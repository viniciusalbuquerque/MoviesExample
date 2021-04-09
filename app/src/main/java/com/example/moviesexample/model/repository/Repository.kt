package com.example.moviesexample.model.repository

interface Repository<T> {
    suspend fun fetch(page: Int): List<T>
    suspend fun getFavorites(): List<T>
    suspend fun getById(id: String): T
    suspend fun search(searchString: String): List<T>
    suspend fun addToFavorite(item: T)
    suspend fun removeFromFavorites(item: T)
    suspend fun getFavById(id: String) : T?
}