package com.example.moviesexample.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesexample.R
import com.example.moviesexample.databinding.MovieListActivityBinding
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.db.remote.OMDbAPI
import com.example.moviesexample.model.repository.MoviesRepository
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.view.adapter.MovieListAdapter
import com.example.moviesexample.viewmodel.MovieListViewModel

class MovieListActivity: AppCompatActivity() {

    private lateinit var binding: MovieListActivityBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter:MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = MovieListViewModel(MoviesRepository(
            OMDbAPI(
                getString(R.string.apiKey)
            )
        ))

        buildRecyclerView()
        viewModel.moviesLiveData.observe(this, Observer {
            AndroidLogger.degub(it.toString())
            refreshList(it)
        })
    }

    private fun buildRecyclerView() {
        adapter = MovieListAdapter()
        binding.movieListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.movieListRecyclerView.adapter = adapter
    }

    private fun refreshList(movies: List<Movie>) {
        adapter.setMovies(movies)
    }
}