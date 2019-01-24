package acker.brian.af_stats

import android.app.Application

class AFStatsApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AFStatsPreferences.init(this@AFStatsApplication)
    }
}