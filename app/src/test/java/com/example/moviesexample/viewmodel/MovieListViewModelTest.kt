package com.example.moviesexample.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.MovieRepositoryMocked
import com.example.moviesexample.model.repository.MoviesRepository
import com.example.moviesexample.model.repository.Repository
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.util.Logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MovieListViewModelTest {

    @Rule @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var logger: Logger

    @Mock
    private lateinit var mockObserver: Observer<List<Movie>>

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var movieRepository: MovieRepositoryMocked

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieRepository = MovieRepositoryMocked()
        movieListViewModel = MovieListViewModel(movieRepository, logger)
        movieListViewModel.moviesLiveData.observeForever(mockObserver)
    }

    @Test
    fun testFetchFunction() {
        movieListViewModel.fetchMovies()
        verify(mockObserver).onChanged(MovieRepositoryMocked.movieList)
    }

    @Test
    fun testSearchFunction() {
        movieListViewModel.searchMovie("")
        verify(mockObserver).onChanged(MovieRepositoryMocked.movieList)
    }

    @Test
    fun testGetMovieById() {
        movieListViewModel.getMovieById("")
        verify(mockObserver).onChanged(MovieRepositoryMocked.movieList.subList(0,1))
    }

}