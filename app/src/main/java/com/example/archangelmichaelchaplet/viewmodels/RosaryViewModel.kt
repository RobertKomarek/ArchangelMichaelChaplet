package com.example.archangelmichaelchaplet.viewmodels

import android.app.Application
import android.content.Context
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.archangelmichaelchaplet.models.CarouselItem
import com.example.archangelmichaelchaplet.models.RosaryDetails
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Locale

class RosaryViewModel(application: Application) : AndroidViewModel(application) {

    /*private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text*/
    val filteredRosaryDetailsListByLanguage = MutableLiveData<List<RosaryDetails>>()
    val rosaryDetailsList = MutableLiveData<List<RosaryDetails>>()
    private var carouselItemList = MutableLiveData<List<CarouselItem>>()
    private val PREFS_NAME = "MyLanguagePreferences"
    private val KEY_SAVED_VALUE ="ChosenLanguage"
    val darkModeEnabled = MutableLiveData<Boolean>()
   /* private val _isDarkMode = MutableLiveData<Boolean>()
    val isDarkMode: LiveData<Boolean>
        get() = _isDarkMode*/

    init {
        loadRosaryDetailsFromJson()
        filterRosaryDetailsByLanguage()
    }

    /*private fun loadCarouselItems(item: RosaryDetails) {
        val currentList = rosaryDetailsList.value ?: emptyList()
        val updatedList = currentList.toMutableList()
        //updatedList.add(item)
        rosaryDetailsList.value = updatedList
        carouselItemList = ArrayList<CarouselItem>()
        //setDarkOrLightMode()
        //val darkModeEnabled = darkModeEnabled.value
        for (rosaryDetail in currentList) {
            if (darkModeEnabled.value == true) {
                val drawableResId = getApplication<Application>().resources.getIdentifier(rosaryDetail.ImageDark, "drawable", context.packageName)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                updatedList.add(rosaryDetail)
            } else {
                val drawableResId = getDrawableResIdFromImageName(rosaryDetail.Image)
                val carouselItem = CarouselItem(drawableResId, rosaryDetail.Chaplet)
                carouselItemList.add(carouselItem)
            }
        }
    }*/

    private fun loadRosaryDetailsFromJson() {
        // Read the JSON file from the assets directory
        val jsonFileName = "SanctiRosariiMichael.json"
        val jsonString = getApplication<Application>().assets.open(jsonFileName).bufferedReader().use { it.readText() }

        // Now you need to parse the JSON string to your List<RosaryDetails> type
        val listType = object : TypeToken<List<RosaryDetails>>() {}.type
        val rosaryDetailsList = Gson().fromJson<List<RosaryDetails>>(jsonString, listType)

        // Set the parsed list to the MutableLiveData
        this.rosaryDetailsList.value = rosaryDetailsList
    }

    // Function to filter the rosaryDetailsList based on a parameter (e.g., language)
    fun filterRosaryDetailsByLanguage() {
        val languageCode:String = Locale.getDefault().language
        val sharedPreferences = getApplication<Application>().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val chosenLanguage : String? = sharedPreferences.getString(KEY_SAVED_VALUE, null)?.toString()

        if (chosenLanguage != null) {
            filteredRosaryDetailsListByLanguage.value = filteredList(chosenLanguage)
            /*filteredRosaryDetailsListByLanguage.value?.let{list ->
                println("$list")
            }*/
        } else {
            filteredRosaryDetailsListByLanguage.value = filteredList(languageCode)
        }

        /*val filteredList = rosaryDetailsList.value?.filter { details ->
            details.Language.contains(language, ignoreCase = true)
        } ?: emptyList()*/
        //filteredRosaryDetailsList.value = filteredList
    }

    private fun filteredList(language: String): List<RosaryDetails> {
        val filteredList = rosaryDetailsList.value?.filter { details ->
            details.Language.contains(language, ignoreCase = true)
        } ?: emptyList()

        return filteredList
    }

    /*private fun getSystemLanguage(languageCode: String): String {
        var language = ""
        //val languageCode = Locale.getDefault().language
        when (languageCode) {
            "en" -> {
                language = "English"
            }
            "de" -> {
                language = "Deutsch"
            }
            "es" -> {
                language = "Español"
            }
            "fr" -> {
                language = "Français"
            }
            "pt" -> {
                language = "Português"
            }
            "it" -> {
                language = "Italiano"
            }
            "zh" -> {
                language = "中文"
            }
            else -> {
                language = "English"
            }
        }
        return language
        *//* English: en
         German: de
         French: fr
         Spanish: es
         Portuguese: pt
         Italian: it
         Chinese: zh*//*
    }*/
}

