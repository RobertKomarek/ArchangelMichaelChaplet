package com.example.archangelmichaelchaplet

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.archangelmichaelchaplet.databinding.ActivityMainBinding
import android.util.Log
import android.view.View

class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*setupWelcomeScreen()
        setupBottomNavigation()*/

        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_rosary, R.id.navigation_promises, R.id.navigation_prayers, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
   /* private fun setupWelcomeScreen() {
        // Show the welcome screen
        binding.frameImageViewWelcome.visibility = View.VISIBLE
        binding.imageViewWelcome.visibility = View.VISIBLE
        binding.textViewWelcomeDescription.visibility = View.VISIBLE
        binding.btnContinueWelcomeScreen.visibility = View.VISIBLE
        binding.btnContinueWelcomeScreen.visibility = View.VISIBLE
        //binding.ConstrainLayoutWelcome.visibility = View.VISIBLE
        //binding.continueButton.visibility = View.VISIBLE

        // Hide the welcome screen when the continue button is clicked
        binding.btnContinueWelcomeScreen.setOnClickListener {
            binding.frameImageViewWelcome.visibility = View.GONE
            binding.imageViewWelcome.visibility = View.GONE
            binding.textViewWelcomeDescription.visibility = View.GONE
            binding.btnContinueWelcomeScreen.visibility = View.GONE
            binding.textViewWelcome.visibility = View.GONE
        }
    }
    private fun setupBottomNavigation() {
        val navView: BottomNavigationView = binding.navView
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_rosary, R.id.navigation_promises, R.id.navigation_prayers, R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }*/
}