package com.example.archangelmichaelchaplet.controllers

import ViewPagerIndulgencesPromisesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.databinding.FragmentPrayersBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.tabs.TabLayoutMediator

class PrayersFragment : Fragment() {
    private var _binding: FragmentPrayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPrayersBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext())

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

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}