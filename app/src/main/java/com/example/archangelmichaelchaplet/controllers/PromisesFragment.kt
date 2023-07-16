package com.example.archangelmichaelchaplet.controllers

import ViewPagerIndulgencesPromisesAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.tabs.TabLayoutMediator

class PromisesFragment : Fragment() {
    private var _binding: FragmentPromisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPromisesBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext())

        val fragmentList = arrayListOf(
            IndulgencesPiusTab(),
            PromisesMichaelTab()
        )

        val adapter = ViewPagerIndulgencesPromisesAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        binding.viewPagerPromisesIndulgences.adapter = adapter

        TabLayoutMediator(binding.tabLayoutPromisesIndulgences, binding.viewPagerPromisesIndulgences) { tab, position ->
            // Set the tab text based on the position
            when (position) {
                0 -> tab.text = rosaryDetails[0].TitlePromisesMichael
                1 -> tab.text = rosaryDetails[0].TitlePromisesIndulgences
            }
        }.attach()

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}