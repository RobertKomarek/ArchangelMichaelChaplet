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
import com.example.archangelmichaelchaplet.databinding.TabPromisesMichaelBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import java.util.Locale

class PromisesMichaelTab: Fragment() {
    private var _binding: TabPromisesMichaelBinding? = null
    private val binding get() = _binding!!
    private lateinit var carouselItemList : ArrayList<CarouselItem>
    private lateinit var sharedPreferences : SharedPreferences
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE ="ChosenLanguage"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TabPromisesMichaelBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Call the Rosary Details. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device
        carouselItemList = ArrayList<CarouselItem>()
        val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage:String = sharedPreferences.getString(KEY_SAVED_VALUE, null).toString()

        val rosaryDetails : List<RosaryDetails>
        if (chosenLanguage.isNotEmpty()) {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }

        binding.imageHeerEngel.setImageResource(R.drawable.heer_engel)
        binding.textViewPromises.text = rosaryDetails[0].PromisesMichael

        return rootView
    }
}