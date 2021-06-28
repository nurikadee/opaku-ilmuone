package com.nurika.opaku.data.repository.local.pref

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(val context: Context) {

    private val prefsName = "SharedPref"
    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    companion object {
        const val USER_IS_WAS_LOGIN = "user_is_was_login"
        const val USER_ID_KEY = "user_id"
        const val USER_EMAIL_KEY = "user_email"
        const val USER_NAME_KEY = "user_name"
    }

    fun saveLoginAccount(email: String, name: String, id: String) {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.putString(USER_ID_KEY, id)
        editor.putString(USER_EMAIL_KEY, email)
        editor.putString(USER_NAME_KEY, name)
        editor.putBoolean(USER_IS_WAS_LOGIN, true)
        editor.apply()
    }

    fun getIsWasLogin(): Boolean? {
        return sharedPref.getBoolean(USER_IS_WAS_LOGIN, false)
    }

    fun clearValue() {
        val editor: SharedPreferences.Editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}