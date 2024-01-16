package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentWelcomeBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import java.util.Locale

class WelcomeFragment : Fragment() {

    private lateinit var carouselItemList : ArrayList<CarouselItem>
    private lateinit var loadedDetails : List<RosaryDetails>
    private val PREFS_LANGUAGE = "MyLanguagePreferences"
    private val KEY_CHOSEN_LANGUAGE ="ChosenLanguage"
    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Disable the action bar (with the name of the selected tab e.g. Rosary)
        val activity = requireActivity() as? AppCompatActivity
        activity?.supportActionBar?.hide()

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        // Load the custom font from assets/fonts directory
        val typeface = Typeface.createFromAsset(requireContext().assets, "fonts/lora_regular.ttf")
        // Set the custom font to the TextView
        binding.textViewWelcome.typeface = typeface

        //Call the Rosary text. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device
        carouselItemList = ArrayList<CarouselItem>()
        val languageCode:String = Locale.getDefault().language
        sharedPreferences =
            requireActivity().getSharedPreferences(PREFS_LANGUAGE, Context.MODE_PRIVATE)
        val chosenLanguage : String? = sharedPreferences.getString(KEY_CHOSEN_LANGUAGE, null)
        if (chosenLanguage != null) {
            loadedDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            loadedDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }

        //Set text for textviews and start button
        binding.btnToRosary.text = loadedDetails[0].ChapletStart
        binding.textViewWelcome.text = loadedDetails[0].AppWelcomeTitle
        binding.textViewExplanation.text = loadedDetails[0].AppWelcomeText

        binding.btnToRosary.setOnClickListener() {
            findNavController().navigate(R.id.action_fragment_welcome_to_navigation_rosary)
        }

        return binding.root
    }
}