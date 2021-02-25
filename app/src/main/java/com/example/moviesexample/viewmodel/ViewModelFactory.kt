package com.example.moviesexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviesexample.model.repository.Repository
import com.example.moviesexample.util.Logger

class ViewModelFactory<R>(private val repository: Repository<R>, private val logger: Logger):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java, Logger::class.java)
            .newInstance(repository, logger)
    }
}