package com.example.moviesexample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesexample.databinding.MovieListFragmentBinding
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.view.adapter.MovieListAdapter
import com.example.moviesexample.viewmodel.MovieListViewModel
import com.example.moviesexample.viewmodel.ViewModelProvider

class ListMoviesFragment: Fragment() {

    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter:MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MovieListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider.createMovieListViewModel(view.context.applicationContext, this)

        buildRecyclerView()
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            AndroidLogger.degub(it.toString())
            refreshList(it)
        })

        lifecycleScope.launchWhenStarted {
            viewModel.fetchMovies()
        }
    }

    private fun buildRecyclerView() {
        adapter = MovieListAdapter()
        binding.movieListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.movieListRecyclerView.adapter = adapter
    }

    private fun refreshList(movies: List<Movie>) {
        adapter.setMovies(movies)
    }

}