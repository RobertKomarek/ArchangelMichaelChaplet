package com.example.archangelmichaelchaplet.controllers

import ViewPagerIndulgencesPromisesAdapter
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.archangelmichaelchaplet.databinding.FragmentPrayersBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.example.archangelmichaelchaplet.viewmodels.RosaryViewModel
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale

class PrayersFragment : Fragment() {
    private var _binding: FragmentPrayersBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: RosaryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPrayersBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Call the Rosary Details. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device
      /*  carouselItemList = ArrayList<CarouselItem>()
        val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage: String? = sharedPreferences.getString(KEY_SAVED_VALUE, null)?.toString()

        if (chosenLanguage != null) {
             rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }*/

        /*val fragmentList = arrayListOf(
            PrayerLeoTab(),
            PrayerLitanyTab()
        )

        val adapter = ViewPagerIndulgencesPromisesAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
        binding.viewPagerPrayers.adapter = adapter

        TabLayoutMediator(binding.tabLayoutPrayers, binding.viewPagerPrayers) { tab, position ->
            // Set the tab text based on the position
            when (position) {
                0 -> tab.text = rosaryDetails[0].TitlePrayersLeo
                1 -> tab.text = rosaryDetails[0].TitlePrayersLitany
            }
        }.attach()*/

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) {rosaryDetails: List<RosaryDetails> ->
            val fragmentList = arrayListOf(
                PrayerLeoTab(),
                PrayerLitanyTab()
            )

            val adapter = ViewPagerIndulgencesPromisesAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)
            binding.viewPagerPrayers.adapter = adapter

            TabLayoutMediator(binding.tabLayoutPrayers, binding.viewPagerPrayers) { tab, position ->
                // Set the tab text based on the position
                when (position) {
                    0 -> tab.text = rosaryDetails[0].TitlePrayersLeo
                    1 -> tab.text = rosaryDetails[0].TitlePrayersLitany
                }
            }.attach()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}