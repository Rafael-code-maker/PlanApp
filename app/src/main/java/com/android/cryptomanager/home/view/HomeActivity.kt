package com.android.cryptomanager.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.android.cryptomanager.R
import com.android.cryptomanager.databinding.HomeActivityBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.home_initial_fragment)
        binding.bottomNavigationHome.setupWithNavController(navController)
    }

}
