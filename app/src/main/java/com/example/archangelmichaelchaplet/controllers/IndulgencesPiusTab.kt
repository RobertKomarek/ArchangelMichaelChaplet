package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails
import java.util.Locale

class IndulgencesPiusTab : Fragment() {
    private var _binding: TabIndulgencesPiusBinding? = null
    private val binding get() = _binding!!
    private lateinit var rosaryDetails: List<RosaryDetails>
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE = "ChosenLanguage"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabIndulgencesPiusBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Call the Rosary Details. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device
        val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage : String? = sharedPreferences.getString(KEY_SAVED_VALUE, null)?.toString()

        if (chosenLanguage != null) {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }

        binding.imagePius.setImageResource(R.drawable.piusix)
        binding.textViewIndulgences.text = rosaryDetails[0].PromisesIndulgences

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Important: Release the binding to avoid memory leaks
        _binding = null
    }
}

