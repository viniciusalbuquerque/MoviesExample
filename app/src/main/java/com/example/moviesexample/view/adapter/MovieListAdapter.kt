package com.example.moviesexample.view.adapter

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesexample.R
import com.example.moviesexample.databinding.MovieListItemBinding
import com.example.moviesexample.model.data.Movie

class MovieListAdapter(private val movieClickedListener: OnMovieItemClickListener): ListAdapter<Movie, MovieListAdapter.ViewHolder>(MovieDiffUtilCallback()) {

    private lateinit var binding: MovieListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        binding = MovieListItemBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.buildView(getItem(position))
        holder.itemView.setOnClickListener {
            movieClickedListener.movieClicked(currentList[position])
        }
    }

    fun submitMovies(list: List<Movie>) {
        val totalList: MutableList<Movie> = currentList.toMutableList()
        totalList.addAll(list)
        submitList(totalList)
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

            binding.root.setOnClickListener {
            }
        }
    }

    class MovieDiffUtilCallback: DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }
}
