package com.example.moviesexample.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesexample.databinding.MainAcitivityBinding

class MovieListActivity: AppCompatActivity() {

    private lateinit var binding: MainAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieListFragment = ListMoviesFragment()
//        val favMoviesFragment = FavoriteMoviesFragment()
        supportFragmentManager.beginTransaction().apply {
            add(binding.fragmentContainerView.id, movieListFragment)
        }.commit()
    }
}