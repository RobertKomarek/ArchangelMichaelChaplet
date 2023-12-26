package com.example.archangelmichaelchaplet.controllers

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.archangelmichaelchaplet.MainActivity
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentPromisesBinding
import com.example.archangelmichaelchaplet.databinding.TabIndulgencesPiusBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.example.archangelmichaelchaplet.viewmodels.RosaryViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import java.util.Locale

class IndulgencesPiusTab : Fragment() {
    private var _binding: TabIndulgencesPiusBinding? = null
    private val binding get() = _binding!!
   /* private lateinit var carouselItemList: ArrayList<CarouselItem>
    private lateinit var rosaryDetails: List<RosaryDetails>
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE = "ChosenLanguage"*/
    //private val sharedViewModel: RosaryViewModel by activityViewModels()
    // Initialize the ViewModel using ViewModelProvider
    /*private val sharedViewModel: RosaryViewModel by lazy {
       ViewModelProvider(this)[RosaryViewModel::class.java]
    }*/

    //lateinit var  sharedViewModel: RosaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TabIndulgencesPiusBinding.inflate(inflater, container, false)
        val rootView = binding.root
        //Call the Rosary Details. Check if language was changed and saved to shared preferences.
        //Otherwise use default language of device

        //carouselItemList = ArrayList<CarouselItem>()

        /* val languageCode = Locale.getDefault().language
        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage : String? = sharedPreferences.getString(KEY_SAVED_VALUE, null)?.toString()

        if (chosenLanguage != null) {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), chosenLanguage)
        } else {
            rosaryDetails = RosaryDetails.loadRosaryDetails(requireContext(), languageCode)
        }*/

        /*sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            binding.textViewIndulgences.text = rosaryDetails[0].PromisesIndulgences
        }*/
        binding.imagePius.setImageResource(R.drawable.piusix)

        return rootView
    }

   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            binding.textViewIndulgences.text = rosaryDetails[0].PromisesIndulgences
            //println(rosaryDetails)
        }
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // Access the shared ViewModel from the activity and observe the data
       val activity = requireActivity() as MainActivity
       val sharedViewModel = activity.sharedViewModel
       /*sharedViewModel.getSharedData().observe(viewLifecycleOwner, Observer { data ->
           // Update your UI using the shared data
           binding.textView.text = data
       })*/
        //sharedViewModel = ViewModelProvider(requireActivity()).get(RosaryViewModel::class.java)
        sharedViewModel.filteredRosaryDetailsListByLanguage.observe(viewLifecycleOwner) { rosaryDetails: List<RosaryDetails> ->
            binding.textViewIndulgences.text = rosaryDetails[0].PromisesIndulgences
            //println(rosaryDetails)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Important: Release the binding to avoid memory leaks
        _binding = null
    }
}

