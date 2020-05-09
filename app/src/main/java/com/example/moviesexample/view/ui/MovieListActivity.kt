package com.example.moviesexample.view.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesexample.databinding.MovieListActivityBinding
import com.example.moviesexample.model.Movie
import com.example.moviesexample.model.repository.MoviesRepository
import com.example.moviesexample.view.adapter.MovieListAdapter
import com.example.moviesexample.viewmodel.MovieListViewModel

class MovieListActivity: AppCompatActivity() {

    private lateinit var binding: MovieListActivityBinding
    private val viewModel = MovieListViewModel(MoviesRepository())
    private lateinit var adapter:MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        buildRecyclerView()
        viewModel.moviesLiveData.observe(this, Observer {
            Log.d("vinicius", it.toString())
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