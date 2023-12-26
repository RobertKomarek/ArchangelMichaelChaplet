package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.databinding.TabPromisesMichaelBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.example.archangelmichaelchaplet.viewmodels.RosaryViewModel
import java.util.Locale

class PromisesMichaelTab: Fragment() {
    private var _binding: TabPromisesMichaelBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: RosaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TabPromisesMichaelBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.imageHeerEngel.setImageResource(R.drawable.heer_engel)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            binding.textViewPromises.text = rosaryDetails[0].PromisesMichael
        }
    }
}