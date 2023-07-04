package com.example.archangelmichaelchaplet.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.databinding.FragmentPrayersBinding

class PrayersFragment : Fragment() {
    private var _binding: FragmentPrayersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPrayersBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Access views using the binding object
        binding.textPrayers.text = "Das sind die Gebete oder Prayers"

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}