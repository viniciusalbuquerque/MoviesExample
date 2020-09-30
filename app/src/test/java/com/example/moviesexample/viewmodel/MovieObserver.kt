package com.example.moviesexample.viewmodel

import androidx.lifecycle.Observer
import com.example.moviesexample.model.data.Movie

interface MovieObserver: Observer<List<Movie>>