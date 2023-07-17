package com.example.archangelmichaelchaplet.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.databinding.TabPrayerLeoxiiiBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails

class PrayerLeoTab : Fragment() {
    //private var _binding: TabIndulgencesPiusBinding? = null
    private var _binding: TabPrayerLeoxiiiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TabPrayerLeoxiiiBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext())
        binding.imageLeoXiii.setImageResource(R.drawable.leoxiii)
        binding.textViewPrayerLeo.text = rosaryDetails[0].PrayersLeo

        return rootView
    }
}