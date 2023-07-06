package com.example.archangelmichaelchaplet.models

import android.content.Context
import androidx.fragment.app.Fragment
import java.util.Locale
import com.google.gson.Gson
import java.io.InputStreamReader
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader

data class RosaryDetails (
    val Number: Int,
    val AppName: String,
    val TabBarRosary: String,
    val TabBarManual: String,
    val TabBarPromises: String,
    val TabBarPrayers: String,
    val TabBarSettings: String,
    val SettingsText: String,
    val TitlePromisesMichael: String,
    val PromisesMichael: String,
    val TitlePromisesIndulgences: String,
    val PromisesIndulgences: String,
    val TitlePrayersLitany: String,
    val PrayersLitany: String,
    val TitlePrayersLeo: String,
    val PrayersLeo: String,
    val PrayersLeoLatin: String,
    val Language: String,
    val AppWelcomeTitle: String,
    val AppWelcomeText: String,
    val ChapletStart: String,
    val Chaplet: String,
    val Image: String,
    val ImageDark: String
    )

{
    companion object {
        fun loadRosaryDetails(context: Context): List<RosaryDetails> {
            val jsonFile = "SanctiRosariiMichael.json"
            val jsonInputStream = context.assets.open(jsonFile)
            val jsonReader = BufferedReader(InputStreamReader(jsonInputStream))
            val jsonText = jsonReader.readText()
            jsonReader.close()

            val gson = Gson()
            val listType = object : TypeToken<List<RosaryDetails>>() {}.type
            val rosaryDetailsList: List<RosaryDetails> = gson.fromJson(jsonText, listType)

            val filteredList = rosaryDetailsList.filter { details ->
                details.Language.contains(getSystemLanguage())
            }

            return filteredList
        }

        private fun getSystemLanguage(): String {
            var language = ""
            val languageCode = Locale.getDefault().language
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
           /* English: en
            German: de
            French: fr
            Spanish: es
            Portuguese: pt
            Italian: it
            Chinese: zh*/
        }
    }
}
