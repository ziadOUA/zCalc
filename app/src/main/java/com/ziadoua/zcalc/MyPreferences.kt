package com.ziadoua.zcalc

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.preference.PreferenceManager
import com.google.gson.Gson

class MyPreferences(context: Context) {

    // https://proandroiddev.com/dark-mode-on-android-app-with-kotlin-dc759fc5f0e1
    companion object {
        private const val THEME = "ziadoua.zcalc.THEME"
        private const val FORCE_DAY_NIGHT = "ziadoua.zcalc.FORCE_DAY_NIGHT"

        private const val KEY_VIBRATION_STATUS = "ziadoua.zcalc.KEY_VIBRATION_STATUS"
        private const val KEY_HISTORY = "ziadoua.zcalc.HISTORY"
        private const val KEY_PREVENT_PHONE_FROM_SLEEPING = "ziadoua.zcalc.PREVENT_PHONE_FROM_SLEEPING"
        private const val KEY_HISTORY_SIZE = "ziadoua.zcalc.HISTORY_SIZE"
        private const val KEY_SCIENTIFIC_MODE_ENABLED_BY_DEFAULT = "ziadoua.zcalc.SCIENTIFIC_MODE_ENABLED_BY_DEFAULT"
        private const val KEY_RADIANS_INSTEAD_OF_DEGREES_BY_DEFAULT = "ziadoua.zcalc.RADIANS_INSTEAD_OF_DEGREES_BY_DEFAULT"
        private const val KEY_NUMBER_PRECISION = "ziadoua.zcalc.NUMBER_PRECISION"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var theme = preferences.getInt(THEME, -1)
        set(value) = preferences.edit().putInt(THEME, value).apply()
    var forceDayNight = preferences.getInt(FORCE_DAY_NIGHT, MODE_NIGHT_UNSPECIFIED)
        set(value) = preferences.edit().putInt(FORCE_DAY_NIGHT, value).apply()

    var vibrationMode = preferences.getBoolean(KEY_VIBRATION_STATUS, true)
        set(value) = preferences.edit().putBoolean(KEY_VIBRATION_STATUS, value).apply()
    var scientificMode = preferences.getBoolean(KEY_SCIENTIFIC_MODE_ENABLED_BY_DEFAULT, false)
        set(value) = preferences.edit().putBoolean(KEY_SCIENTIFIC_MODE_ENABLED_BY_DEFAULT, value).apply()
    var useRadiansByDefault = preferences.getBoolean(KEY_RADIANS_INSTEAD_OF_DEGREES_BY_DEFAULT, false)
        set(value) = preferences.edit().putBoolean(KEY_RADIANS_INSTEAD_OF_DEGREES_BY_DEFAULT, value).apply()
    private var history = preferences.getString(KEY_HISTORY, null)
        set(value) = preferences.edit().putString(KEY_HISTORY, value).apply()
    var preventPhoneFromSleepingMode = preferences.getBoolean(KEY_PREVENT_PHONE_FROM_SLEEPING, false)
        set(value) = preferences.edit().putBoolean(KEY_PREVENT_PHONE_FROM_SLEEPING, value).apply()
    var historySize = preferences.getString(KEY_HISTORY_SIZE, "100")
        set(value) = preferences.edit().putString(KEY_HISTORY_SIZE, value).apply()
    var numberPrecision = preferences.getString(KEY_NUMBER_PRECISION, "10")
        set(value) = preferences.edit().putString(KEY_NUMBER_PRECISION, value).apply()

    fun getHistory(): MutableList<History> {
        val gson = Gson()
        return if (preferences.getString(KEY_HISTORY, null) != null) {
            gson.fromJson(history, Array<History>::class.java).asList().toMutableList()
        } else {
            mutableListOf()
        }
    }

    fun saveHistory(context: Context, history: List<History>){
        val gson = Gson()
        val history2 = history.toMutableList()
        while (historySize!!.toInt() > 0 && history2.size > historySize!!.toInt()) {
            history2.removeAt(0)
        }
        MyPreferences(context).history = gson.toJson(history2) // Convert to json
    }
}
