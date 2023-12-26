package com.example.archangelmichaelchaplet.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DarkLightModeViewModel : ViewModel() {
    val darkModeEnabled = MutableLiveData<Boolean>()
    //var dakModeEnabled: Boolean = false
    /*private val _darkModeEnabled =  MutableLiveData<Boolean>()
    val darkModeEnabled: LiveData<Boolean> get() = _darkModeEnabled

    fun setDarkMode(value: Boolean) {
        _darkModeEnabled.value = value
    }*/
}