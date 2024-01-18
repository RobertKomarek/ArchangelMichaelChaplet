package com.example.archangelmichaelchaplet.controllers

import TabsAdapterPromisesIndulgences
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale

class PromisesIndulgencesFragment : Fragment() {
    private var _binding: FragmentPromisesBinding? = null
    private val binding get() = _binding!!
    //TabLayout for Promises Michael & Indulgences from Pope Pius
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE = "ChosenLanguage"
    private lateinit var rosaryDetails: List<RosaryDetails>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromisesBinding.inflate(inflater, container, false)

        val view = inflater.inflate(R.layout.fragment_promises, container, false)
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)

        // Set up the ViewPager2
        viewPager.adapter = TabsAdapterPromisesIndulgences(this)

        //Call the Rosary Details. Check if language was changed and saved to shared preferences. Otherwise use default language of device
        val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage: String? = sharedPreferences.getString(KEY_SAVED_VALUE, null)?.toString()

        if (chosenLanguage != null) {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }

        // Set up the TabLayout with the ViewPager2
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            // Customize tab names if needed
            tab.text = when (position) {
                0 -> rosaryDetails[0].TitlePromisesMichael
                1 -> rosaryDetails[0].TitlePromisesIndulgences
                else -> "Tab $position"
            }
        }.attach()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}