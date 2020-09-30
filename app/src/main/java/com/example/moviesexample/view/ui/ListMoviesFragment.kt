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
import com.example.moviesexample.util.Logger
import com.example.moviesexample.view.adapter.LinearLayoutManagerImpl
import com.example.moviesexample.view.adapter.MovieListAdapter
import com.example.moviesexample.view.listeners.EndlessScrollListener
import com.example.moviesexample.viewmodel.MovieListViewModel
import com.example.moviesexample.viewmodel.ViewModelProvider

class ListMoviesFragment: Fragment() {

    private val TAG = "ListMoviesFragment"
    private lateinit var binding: MovieListFragmentBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var adapter: MovieListAdapter
    private lateinit var logger: Logger

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
        logger = AndroidLogger()

        buildRecyclerView()
        viewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            logger.debug(TAG, it.toString())
            refreshList(it)
        })

        lifecycleScope.launchWhenStarted {
            loadNext()
        }
    }

    private fun buildRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        val linearLayoutManager = LinearLayoutManagerImpl(layoutManager)

        adapter = MovieListAdapter()
        binding.movieListRecyclerView.layoutManager = layoutManager
        binding.movieListRecyclerView.adapter = adapter
        binding.movieListRecyclerView.addOnScrollListener(
            EndlessScrollListener(linearLayoutManager, { loadNext() }, AndroidLogger())
        )
    }

    private fun refreshList(movies: List<Movie>) {
        adapter.submitMovies(movies)
    }

    private fun loadNext() {
        logger.debug(TAG, "loadNext called")
        viewModel.fetchNextPage()
    }

}