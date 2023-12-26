package com.example.archangelmichaelchaplet

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.archangelmichaelchaplet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.archangelmichaelchaplet.viewmodels.RosaryViewModel


class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sharedViewModel: RosaryViewModel
    private val PREFS_NAME = "MyPrefs"
    private val SHOW_MESSAGE_KEY = "ShowMessage"
    //private val rosaryDetailsViewModel: RosaryDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        sharedViewModel = ViewModelProvider(this).get(RosaryViewModel::class.java)

        //Disable Landscape Orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        // Handle the splash screen transition.
        //Thread.sleep(2000)
        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}