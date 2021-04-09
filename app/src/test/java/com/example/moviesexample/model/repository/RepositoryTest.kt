package com.example.moviesexample.model.repository

import androidx.annotation.VisibleForTesting
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.datasource.remote.omdb.MovieOMDb
import com.example.moviesexample.model.datasource.local.room.MoviesRoomDao
import com.example.moviesexample.model.datasource.remote.MockedRemoteAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

class RepositoryTest {

    private val movieRemoteList = MockedRemoteAPI.movieRemoteList
    private val remoteApi = MockedRemoteAPI()
    private lateinit var localDB: MoviesRoomDao

    @Before
    fun setup() {
        localDB = mock(MoviesRoomDao::class.java)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_all_movies_test() {
        runBlockingTest {
            val repository: Repository<Movie> = MoviesRepository(localDB, remoteApi)
            repository.fetch(1).run {
                forEachIndexed { index, movie ->
                    assert(movieEqualsMovieRemote(movie, movieRemoteList[index]))
                }
            }
        }
    }

    @VisibleForTesting
    private fun movieEqualsMovieRemote(movie: Movie, movieRemote: MovieOMDb): Boolean {
        return movie.id == movieRemote.id &&
                movie.name == movieRemote.title &&
                movie.posterUrl == movieRemote.posterUrl
    }
}