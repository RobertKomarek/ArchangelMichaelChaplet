package com.example.archangelmichaelchaplet.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.databinding.FragmentPrayersBinding
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.databinding.FragmentRosaryBinding

class PromisesFragment : Fragment() {
    private var _binding: FragmentPromisesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPromisesBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Access views using the binding object
        binding.textPromises.text = "Das sind die Versprechungen oder Promises"

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}