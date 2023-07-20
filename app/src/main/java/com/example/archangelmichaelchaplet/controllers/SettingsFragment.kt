package com.example.archangelmichaelchaplet.controllers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentPrayersBinding
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.databinding.FragmentRosaryBinding
import com.example.archangelmichaelchaplet.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private val radioButtons = ArrayList<RadioButton>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Access views using the binding object
        binding.imageEnglish.setImageResource(R.drawable.lang_english)
        binding.imageFrench.setImageResource(R.drawable.lang_french)

        // Add radio buttons to the array
        radioButtons.add(binding.radioButtonEnglish)
        radioButtons.add(binding.radioButtonFrench)
        radioButtons.add(binding.radioButtonGerman)
        radioButtons.add(binding.radioButtonItalian)
        radioButtons.add(binding.radioButtonSpanish)
        radioButtons.add(binding.radioButtonPortugese)
        radioButtons.add(binding.radioButtonChinese)

        // Set click listeners for each radio button
        for (radioButton in radioButtons) {
            radioButton.setOnClickListener {
                // Loop through the radio buttons and uncheck all except the clicked one
                for (rb in radioButtons) {
                    rb.isChecked = rb == radioButton
                }
            }
        }

        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}