package com.example.moviesexample.model.datasource.remote

import com.example.moviesexample.model.datasource.remote.omdb.MovieOMDb

class MockedRemoteAPI : RemoteDataSource<MovieOMDb> {
    companion object {
        public val movieRemoteList = listOf(
            MovieOMDb("1", "Title1", "", ""),
            MovieOMDb("2", "Title2", "", "")
        )
    }

    override suspend fun get(page: Int) = movieRemoteList

    override suspend fun search(searchString: String, page: Int) = movieRemoteList.filter {
        it.title.contains(searchString)
    }

    override suspend fun getById(id: String) = movieRemoteList.filter {
        it.id == id
    }[0]

}