package com.example.moviesexample.model.db.remote

import com.example.moviesexample.model.data.MovieRemote

class MockedRemoteAPI : DBService<MovieRemote> {
    companion object {
        public val movieRemoteList = listOf(
            MovieRemote("1", "Title1", "", ""),
            MovieRemote("2", "Title2", "", "")
        )
    }

    override suspend fun get(page: Int) = movieRemoteList

    override suspend fun search(searchString: String, page: Int) = movieRemoteList

    override suspend fun getById(id: String) = movieRemoteList[0]

}