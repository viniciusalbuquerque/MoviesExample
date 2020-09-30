package com.example.moviesexample.model.repository

interface Repository<T> {
    suspend fun fetch(): List<T>
    suspend fun getFavorites(): List<T>
    suspend fun getById(id: String): T
    suspend fun search(searchString: String): List<T>
}