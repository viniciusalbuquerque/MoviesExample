package com.example.moviesexample.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesexample.R
import com.example.moviesexample.databinding.MovieListItemBinding
import com.example.moviesexample.model.data.Movie

class MovieListAdapter(): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var binding: MovieListItemBinding
    private val movies: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        binding = MovieListItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return this.movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.buildView(movies[position])
    }

    public fun setMovies(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun buildView(movie: Movie) {
            binding.itemMovieName.text = movie.name

            if (movie.imdbRating == null) {
                binding.itemMovieReview.visibility = View.GONE
                binding.labelMovieReview.visibility = View.GONE
            } else {
                binding.itemMovieReview.text = movie.imdbRating
            }

            if (!TextUtils.isEmpty(movie.posterUrl)) {
                Glide.with(itemView)
                    .load(movie.posterUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .circleCrop()
                    .into(binding.itemMovieImage)
            }
        }
    }
}
