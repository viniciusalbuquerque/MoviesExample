package com.example.moviesexample.usecases

interface UseCase<P,R> {
   suspend fun execute(param: P): R
}