package com.example.archangelmichaelchaplet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.archangelmichaelchaplet.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val PREFS_NAME = "MyPrefs"
    private val SHOW_MESSAGE_KEY = "ShowMessage"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

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

    fun showMessageDialog(context: Context): Boolean {
        var showNextTime = true
        //val loadedDetails = RosaryDetails.loadRosaryDetails(requireContext(ArchangelMichaelChaplet.app.main))

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Message Title")
        builder.setMessage("Message content")

        builder.setPositiveButton("Show Next Time") { _, _ ->
            // User clicked on "Show Next Time" button
            showNextTime = true
        }

        builder.setNegativeButton("Do Not Show") { _, _ ->
            // User clicked on "Do Not Show" button
            showNextTime = false
        }

        val dialog = builder.create()
        dialog.show()

        return showNextTime
    }
}