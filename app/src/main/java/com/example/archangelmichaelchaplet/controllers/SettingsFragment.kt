package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentSettingsBinding
import android.content.pm.PackageManager
import android.graphics.Typeface
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import java.util.Locale


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val radioButtons = ArrayList<RadioButton>()
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE ="ChosenLanguage"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Load the custom font from assets/fonts directory
        val typeface = Typeface.createFromAsset(requireContext().assets, "fonts/lora_regular.ttf")

        // Set the custom font to the TextView
        binding.textViewHeader.typeface = typeface

        // Access views using the binding object
        binding.imageEnglish.setImageResource(R.drawable.lang_english)
        binding.imageFrench.setImageResource(R.drawable.lang_french)
        binding.imagePortugese.setImageResource(R.drawable.lang_portugese)
        binding.imageChinese.setImageResource(R.drawable.lang_chinese)
        binding.imageItalian.setImageResource(R.drawable.lang_italian)
        binding.imageSpanish.setImageResource(R.drawable.lang_spanish)
        binding.imageGerman.setImageResource(R.drawable.lang_german)

        // Add radio buttons to the array
        radioButtons.add(binding.en)
        radioButtons.add(binding.fr)
        radioButtons.add(binding.de)
        radioButtons.add(binding.it)
        radioButtons.add(binding.es)
        radioButtons.add(binding.pt)
        radioButtons.add(binding.zh)

        //Check if language was changed (not default system language) + saved to shared preferences and isChecked = true the respective radio btn.
        // Otherwise get the system language and set the respective radio button to isChecked = true
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val selectedLanguage = sharedPreferences.getString(KEY_SAVED_VALUE, null)
        val defaultLanguage = Locale.getDefault().language
        /*//Set English as default isChecked = true language and update with the following if-statement
        radioButtons[0].isChecked = true*/

        if (selectedLanguage != null) {
            for (radioButton in radioButtons) {
                if (resources.getResourceEntryName(radioButton.id) == selectedLanguage) {
                    radioButton.isChecked = true
                }
            }
        } else {
            //get system language and set respective radio btn to isChecked = true
            for (radioButton in radioButtons) {
                if (resources.getResourceEntryName(radioButton.id) == defaultLanguage) {
                    radioButton.isChecked = true
                } else {
                    radioButton.isChecked = false
                }
            }
        }

        // Set click listeners for each radio button and check the button according to selected language
        //+ change Language + save to shared preferences
        for (radioButton in radioButtons) {
            radioButton.setOnClickListener { onRadioButtonClicked(radioButton) }
        }

        binding.appStoreButton.setOnClickListener {
            var url = "https://apps.apple.com/de/app/sancti-rosarii-michael/id1577365794"
            shareAppLink(url)
        }
        binding.playstoreButton.setOnClickListener {
            var url = "http://play.google.com/store/apps/details?id=com.robertkomarek.sanctirosariimichael"
            shareAppLink(url)
        }
        binding.buttonOpenMailApp.setOnClickListener {
            var subject = "Sancti Rosarii Michael-App"
            var email = "robert.komarek98@gmail.com"
            sendMail(subject, email)
        }

        binding.buttonOpenCreativeCommons.setOnClickListener {
            var url = "https://creativecommons.org"
            openUrl(url)
        }

        binding.buttonOpenPublicDomain.setOnClickListener {
            var url = "https://creativecommons.org/publicdomain/zero/1.0/deed.en"
            openUrl(url)
        }

        return rootView
    }

    private fun onRadioButtonClicked(clickedRadioButton: RadioButton) {
        // Loop through all RadioButtons and update their checked states
        for (radioButton in radioButtons) {
            radioButton.isChecked = radioButton == clickedRadioButton
        }

        // Save the clicked RadioButton's ResourceEntryName to SharedPreferences
        val radioButtonEntryName = resources.getResourceEntryName(clickedRadioButton.id)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_SAVED_VALUE, radioButtonEntryName)
        editor.apply()
    }


    //Safe selected Language to Shared Preferences
    private fun saveSelectedRadioButtonId(context: Context, radioButtonId: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("selectedRadioButtonId", radioButtonId)
        editor.apply()
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun sendMail(subject:String, email:String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }

        // Verify that there's an email app available to handle the intent
        if (sendIntent.resolveActivity(requireContext().packageManager) != null) {
            startActivity(Intent.createChooser(sendIntent, subject))
        } else {
            // Handle the case where no app is available to handle the intent
            // For example, display an error message or provide an alternative action.
        }
    }

    private fun shareAppLink(url: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, url)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}