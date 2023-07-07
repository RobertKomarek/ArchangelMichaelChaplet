package com.example.archangelmichaelchaplet.controllers

import CarouselAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.archangelmichaelchaplet.R
import com.example.archangelmichaelchaplet.databinding.FragmentRosaryBinding
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.snackbar.Snackbar


class RosaryFragment : Fragment() {
    private var _binding: FragmentRosaryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: CarouselAdapter
    private lateinit var carouselItemList : ArrayList<CarouselItem>
    private lateinit var loadedDetails : List<RosaryDetails>
    private lateinit var viewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout

    var darkModeEnabled : Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRosaryBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Disable the action bar (with the name of the selected tab e.g. Rosary)
        val activity = requireActivity() as? AppCompatActivity
        activity?.supportActionBar?.hide()

        val blinkingAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.info_btn_blinking)
        binding.btnInfo.startAnimation(blinkingAnimation)

        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call the Rosary Details
        carouselItemList = ArrayList<CarouselItem>()
        loadedDetails = ArrayList<RosaryDetails>()
        loadedDetails = RosaryDetails.loadRosaryDetails(requireContext())

        binding.textViewWelcome.text = loadedDetails[0].AppWelcomeTitle
        binding.textViewWelcomeDescription.text = loadedDetails[0].AppWelcomeText
        binding.btnContinueWelcomeScreen.text = loadedDetails[0].ChapletStart

        /*binding.frameImageViewWelcome.visibility = View.VISIBLE
        binding.imageViewWelcome.visibility = View.VISIBLE
        binding.textViewWelcomeDescription.visibility = View.VISIBLE
        binding.btnContinueWelcomeScreen.visibility = View.VISIBLE
        binding.btnContinueWelcomeScreen.visibility = View.VISIBLE*/

        binding.btnContinueWelcomeScreen.setOnClickListener {
            binding.frameImageViewWelcome.visibility = View.INVISIBLE
            binding.imageViewWelcome.visibility = View.INVISIBLE
            binding.textViewWelcomeDescription.visibility = View.INVISIBLE
            binding.btnContinueWelcomeScreen.visibility = View.INVISIBLE
            binding.textViewWelcome.visibility = View.INVISIBLE

            viewPager = binding.viewPager
            indicatorContainer = binding.indicatorContainer

            for (rosaryDetail in loadedDetails) {
                if (darkModeEnabled == true) {
                    val drawableResId = getDrawableResIdFromImageName(rosaryDetail.ImageDark)
                    val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                    carouselItemList.add(carouselItem)
                } else {
                    val drawableResId = getDrawableResIdFromImageName(rosaryDetail.Image)
                    val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                    carouselItemList.add(carouselItem)
                }
            }
            adapter = CarouselAdapter(carouselItemList)
            viewPager.adapter = adapter

            // Add indicators for each item in the adapter
            for (i in 0 until adapter.itemCount) {
                val indicatorItem = LayoutInflater.from(requireContext())
                    .inflate(R.layout.indicator_item_layout, indicatorContainer, false)
                indicatorContainer.addView(indicatorItem)
            }

            // Set up the page change listener
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    updateIndicator(position)
                }
            })
        }


       /* viewPager = binding.viewPager
        indicatorContainer = binding.indicatorContainer

        //Call the Rosary Details
        carouselItemList = ArrayList<CarouselItem>()
        loadedDetails = ArrayList<RosaryDetails>()
        loadedDetails = RosaryDetails.loadRosaryDetails(requireContext())

        for (rosaryDetail in loadedDetails) {
            if (darkModeEnabled == true) {
                val drawableResId = getDrawableResIdFromImageName(rosaryDetail.ImageDark)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                carouselItemList.add(carouselItem)
            } else {
                val drawableResId = getDrawableResIdFromImageName(rosaryDetail.Image)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                carouselItemList.add(carouselItem)
            }
        }
        adapter = CarouselAdapter(carouselItemList)
        viewPager.adapter = adapter

        // Add indicators for each item in the adapter
        for (i in 0 until adapter.itemCount) {
            val indicatorItem = LayoutInflater.from(requireContext())
                .inflate(R.layout.indicator_item_layout, indicatorContainer, false)
            indicatorContainer.addView(indicatorItem)
        }

        // Set up the page change listener
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicator(position)
            }
        })*/

        binding.btnDarkNight.setOnClickListener {
            //toggle between darkModeEnable is true and false
            darkModeEnabled = !darkModeEnabled
            setDarkOrLightMode()
        }

        binding.btnStart.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            binding.viewPager.setCurrentItem(0, true)
        }

        binding.btnForward.setOnClickListener {
            // Scroll to the next carousel item
            val currentItem = binding.viewPager.currentItem
            binding.viewPager.setCurrentItem(currentItem + 1, true)
        }

        binding.btnBackward.setOnClickListener {
            // Scroll to the previous carousel item
            val currentItem = binding.viewPager.currentItem
            binding.viewPager.setCurrentItem(currentItem - 1, true)
        }

        binding.btnInfo.setOnClickListener {
        }
    }

    private fun onButton2Clicked() {
        TODO("Not yet implemented")
    }

    private fun onButton1Clicked() {
        TODO("Not yet implemented")
    }

    private fun setDarkOrLightMode() {
        if (darkModeEnabled) {
            //change image of button
            binding.btnDarkNight.setImageResource(R.drawable.baseline_wb_sunny_24)
            carouselItemList.clear()
            for (rosaryDetail in loadedDetails) {
                val drawableResId = getDrawableResIdFromImageName(rosaryDetail.ImageDark)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                carouselItemList.add(carouselItem)
            }
            adapter.notifyDataSetChanged()
        } else {
            //change image of button
            binding.btnDarkNight.setImageResource(R.drawable.baseline_dark_mode_24)
            carouselItemList.clear()
            for (rosaryDetail in loadedDetails) {
                val drawableResId = getDrawableResIdFromImageName(rosaryDetail.Image)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                carouselItemList.add(carouselItem)
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun getDrawableResIdFromImageName(imageName: String): Int {
        val resources = resources ?: return 0
        return resources.getIdentifier(imageName, "drawable", requireActivity().packageName)
    }

    private fun updateIndicator(position: Int) {
        val indicatorCount = indicatorContainer.childCount
        for (i in 0 until indicatorCount) {
            val indicatorItem = indicatorContainer.getChildAt(i)
            indicatorItem.isSelected = i == position
            // Customize the appearance of the indicator item based on selection
            // For example, change the background color or icon
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Release the binding object
    }
}


