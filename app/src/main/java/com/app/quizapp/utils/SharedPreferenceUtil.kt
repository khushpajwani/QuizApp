package com.app.quizapp.utils

import android.content.Context


class SharedPreferenceUtil {
    private var instance: SharedPreferenceUtil? = null
    private val PREFS_NAME = "default_preferences"

    @Synchronized
    fun getInstance(): SharedPreferenceUtil? {
        if (instance == null) {
            instance = SharedPreferenceUtil()
        }
        return instance
    }

    fun getToken(context: Context): String? {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_TOKEN, "")
    }

    fun setToken(context: Context, value: String) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_TOKEN, value).apply()
    }
}