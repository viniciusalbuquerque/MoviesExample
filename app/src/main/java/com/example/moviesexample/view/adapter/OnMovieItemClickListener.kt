package com.example.moviesexample.view.adapter

import com.example.moviesexample.model.data.Movie

fun interface OnMovieItemClickListener {
    fun movieClicked(movie: Movie)
}