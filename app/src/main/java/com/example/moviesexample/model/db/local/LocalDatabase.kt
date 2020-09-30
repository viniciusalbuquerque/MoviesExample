package com.example.moviesexample.model.db.local

interface LocalDatabase<T> {

    suspend fun getAll(): List<T>

}