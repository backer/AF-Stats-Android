package acker.brian.af_stats.event

import android.os.Bundle

class LaunchScreenEvent(val screenType: ScreenType) {
    var args: Bundle? = null

    enum class ScreenType {
        STATS_WEBVIEW
    }
}