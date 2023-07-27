package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
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

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var carouselItemList : ArrayList<CarouselItem>
    private lateinit var sharedPreferences : SharedPreferences
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE ="ChosenLanguage"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Disable the action bar (with the name of the selected tab e.g. Rosary)
        val activity = requireActivity() as? AppCompatActivity
        activity?.supportActionBar?.hide()

        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call the Rosary Details. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device
        carouselItemList = ArrayList<CarouselItem>()
        val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage:String = sharedPreferences.getString(KEY_SAVED_VALUE, null).toString()

        lateinit var rosaryDetails : List<RosaryDetails>
        if (chosenLanguage.isNotEmpty()) {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }

        binding.btnToRosary.text = rosaryDetails[0].ChapletStart
        binding.textViewWelcome.text = rosaryDetails[0].AppWelcomeTitle
        binding.textViewExplanation.text = rosaryDetails[0].AppWelcomeText

        binding.btnToRosary.setOnClickListener() {
            findNavController().navigate(R.id.action_fragment_welcome_to_navigation_rosary)
        }
    }

}