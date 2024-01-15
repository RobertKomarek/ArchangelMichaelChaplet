package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.databinding.TabPrayerLitanyBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import java.util.Locale

class PrayerLitanyTab : Fragment() {
    private var _binding: TabPrayerLitanyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = TabPrayerLitanyBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.imageLitany.setImageResource(R.drawable.background_litany_settings)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) {rosaryDetails: List<RosaryDetails> ->
            binding.textViewLitany.text = rosaryDetails[0].PrayersLitany
        }*/
    }
}