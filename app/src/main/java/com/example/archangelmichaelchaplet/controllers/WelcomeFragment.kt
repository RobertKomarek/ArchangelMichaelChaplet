package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentWelcomeBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import java.util.Locale

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Disable the action bar (with the name of the selected tab e.g. Rosary)
        val activity = requireActivity() as? AppCompatActivity
        activity?.supportActionBar?.hide()

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        binding.btnToRosary.setOnClickListener() {
            findNavController().navigate(R.id.action_fragment_welcome_to_navigation_rosary)
        }

        return binding.root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            binding.btnToRosary.text = rosaryDetails[0].ChapletStart
            binding.textViewWelcome.text = rosaryDetails[0].AppWelcomeTitle
            binding.textViewExplanation.text = rosaryDetails[0].AppWelcomeText

            binding.btnToRosary.setOnClickListener() {
                findNavController().navigate(R.id.action_fragment_welcome_to_navigation_rosary)
            }
        }
    }*/
}