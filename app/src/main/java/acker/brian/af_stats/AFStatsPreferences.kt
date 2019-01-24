package acker.brian.af_stats

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class AFStatsPreferences private constructor(context: Application) {
    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        @JvmStatic
        private val FILE_NAME: String = AFStatsPreferences::class.java.`package`!!.name
        private lateinit var instance: AFStatsPreferences

        fun init(context: Application) {
            instance = AFStatsPreferences(context)
        }

        fun getInstance(): AFStatsPreferences {
            return instance
        }
    }
}