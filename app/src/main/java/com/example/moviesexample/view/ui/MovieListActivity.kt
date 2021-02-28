package com.example.moviesexample.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.moviesexample.R
import com.example.moviesexample.databinding.MainAcitivityBinding

class MovieListActivity: AppCompatActivity() {

    private lateinit var binding: MainAcitivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainAcitivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigationView
        val navigationContoller = findNavController(R.id.main_nav_host)
        val appBarConfig = AppBarConfiguration(setOf(R.id.listMoviesFragment, R.id.favoriteMoviesFragment))

        setupActionBarWithNavController(navigationContoller, appBarConfig)
        bottomNavigationView.setupWithNavController(navigationContoller)
    }
}
