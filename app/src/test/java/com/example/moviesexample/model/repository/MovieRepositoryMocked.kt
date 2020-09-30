package com.example.moviesexample.model.repository

import com.example.moviesexample.model.data.Movie

class MovieRepositoryMocked : Repository<Movie> {

    companion object {
        public val movieList = listOf(
            Movie("1", "Title1", ""),
            Movie("2", "Title2", ""),
            Movie("3", "Title3", "")
        )
    }

    override suspend fun fetch(page: Int) = movieList

    override suspend fun getFavorites() = movieList

    override suspend fun getById(id: String) = movieList[0]

    override suspend fun search(searchString: String) = movieList
}