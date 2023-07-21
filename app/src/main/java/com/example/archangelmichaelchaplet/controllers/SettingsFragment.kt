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
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val radioButtons = ArrayList<RadioButton>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Load the custom font from assets/fonts directory
        val typeface = Typeface.createFromAsset(requireContext().assets, "fonts/lora_regular.ttf")

        // Set the custom font to the TextView
        binding.textView.typeface = typeface

        // Access views using the binding object
        binding.imageEnglish.setImageResource(R.drawable.lang_english)
        binding.imageFrench.setImageResource(R.drawable.lang_french)

        // Add radio buttons to the array
        radioButtons.add(binding.radioButtonEnglish)
        radioButtons.add(binding.radioButtonFrench)
        radioButtons.add(binding.radioButtonGerman)
        radioButtons.add(binding.radioButtonItalian)
        radioButtons.add(binding.radioButtonSpanish)
        radioButtons.add(binding.radioButtonPortugese)
        radioButtons.add(binding.radioButtonChinese)

        // Set click listeners for each radio button
        for (radioButton in radioButtons) {
            radioButton.setOnClickListener {
                // Loop through the radio buttons and uncheck all except the clicked one
                for (rb in radioButtons) {
                    rb.isChecked = rb == radioButton
                }
            }
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