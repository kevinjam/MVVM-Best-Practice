package com.kevinjanvier.understandmvvm.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val KEY_SAVE_AT ="key_save_at"
class PreferenceProvider (
    context: Context) {

    private val appContext = context.applicationContext

    private val preference:SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    //save time
    fun saveLastSaved(savedAt:String){
        preference.edit().putString(KEY_SAVE_AT,
            savedAt)
            .apply()
    }

    //get time saved

    fun getLastSaved():String?{
        return preference.getString(KEY_SAVE_AT, null)
    }
}