package com.example.archangelmichaelchaplet.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.databinding.TabPromisesMichaelBinding
import com.example.archangelmichaelchaplet.models.RosaryDetails

class PromisesMichaelTab: Fragment() {
    private var _binding: TabPromisesMichaelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TabPromisesMichaelBinding.inflate(inflater, container, false)
        val rootView = binding.root

        val rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext())
        binding.imageHeerEngel.setImageResource(R.drawable.heer_engel)
        binding.textViewPromises.text = rosaryDetails[0].PromisesMichael


        return rootView
    }
}