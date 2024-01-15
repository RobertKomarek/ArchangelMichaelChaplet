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
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale

class PromisesFragment : Fragment() {
    private var _binding: FragmentPromisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPromisesBinding.inflate(inflater, container, false)
        val rootView = binding.root

        return rootView
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            val fragmentList = arrayListOf(
                PromisesMichaelTab(),
                IndulgencesPiusTab()
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
        }
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}