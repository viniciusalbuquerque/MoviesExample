package com.example.moviesexample.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviesexample.databinding.MovieListFragmentBinding
import com.example.moviesexample.model.data.Movie
import com.example.moviesexample.model.repository.MoviesRepository
import com.example.moviesexample.model.repository.RepositoryFactory
import com.example.moviesexample.usecases.*
import com.example.moviesexample.util.AndroidLogger
import com.example.moviesexample.util.Logger
import com.example.moviesexample.view.adapter.LinearLayoutManagerImpl
import com.example.moviesexample.view.adapter.MovieListAdapter
import com.example.moviesexample.view.listeners.EndlessScrollListener
import com.example.moviesexample.viewmodel.listmovies.MovieListViewModel
import com.example.moviesexample.viewmodel.listmovies.ViewModelFactory

class ListMoviesFragment: Fragment() {

    private val TAG = "ListMoviesFragment"
    private lateinit var binding: MovieListFragmentBinding
    private val viewModel: MovieListViewModel by viewModels()
    private lateinit var adapter: MovieListAdapter
    private lateinit var logger: Logger

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MovieListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
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

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        val repository = RepositoryFactory.getInstance(requireContext())
        return ViewModelFactory(
            FetchMoviesUseCase(repository),
            GetMovieByIdUseCase(repository),
            SearchMovieUseCase(repository),
            AddFavoriteUseCase(repository),
            RemoveFavoriteUseCase(repository),
            AndroidLogger()
        )
    }

    private fun buildRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        val linearLayoutManager = LinearLayoutManagerImpl(layoutManager)

        adapter = MovieListAdapter {
            viewModel.toggleFavorite(it)
        }
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