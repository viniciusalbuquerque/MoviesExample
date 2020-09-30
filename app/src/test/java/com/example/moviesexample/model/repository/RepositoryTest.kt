package com.example.moviesexample.model.repository

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.data.MovieRemote
import com.example.moviesexample.model.db.local.MoviesRoomDao
import com.example.moviesexample.model.db.remote.DBService
import com.example.moviesexample.model.db.remote.OMDbAPI
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.util.Logger
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryTest {

    private val movieRemoteList = listOf(
        MovieRemote("1", "Title1", "", ""),
        MovieRemote("2", "Title2", "", "")
    )

    private lateinit var remoteApi: DBService<MovieRemote>
    private lateinit var localDB: MoviesRoomDao
    private lateinit var logger: Logger

    @Before
    fun setup() {
        remoteApi = mock(DBService::class.java) as DBService<MovieRemote>
        localDB = mock(MoviesRoomDao::class.java)
        logger = mock(Logger::class.java)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun get_all_movies_test() =
        runBlockingTest {
            `when`(remoteApi.get()).thenReturn(movieRemoteList)
            val repository: Repository<Movie> = MoviesRepository(localDB, remoteApi, logger)
            val test = repository.fetch()
            repository.fetch().collect {
                it.forEachIndexed { index, movie ->
                    assert(movieEqualsMovieRemote(movie, movieRemoteList[index]))
                }
            }
        }

    @VisibleForTesting
    private fun movieEqualsMovieRemote(movie: Movie, movieRemote: MovieRemote): Boolean {
        return movie.id == movieRemote.id &&
                movie.name == movieRemote.title &&
                movie.posterUrl == movieRemote.posterUrl
    }
}