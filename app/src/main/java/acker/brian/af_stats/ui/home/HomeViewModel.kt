package acker.brian.af_stats.ui.home

import acker.brian.af_stats.data.model.Game
import acker.brian.af_stats.event.LaunchScreenEvent
import acker.brian.af_stats.ui.web.StatsWebView
import android.databinding.BaseObservable
import android.os.Bundle
import android.view.View
import org.greenrobot.eventbus.EventBus

class HomeViewModel : BaseObservable() {

    fun onFootballButtonClick(view: View) {
        postLaunchScreenEvent(Game.Sport.FOOTBALL.name)
    }

    fun onSoccerButtonClick(view: View) {
        postLaunchScreenEvent(Game.Sport.SOCCER.name)
    }

    private fun postLaunchScreenEvent(sport: String) {
        val event = LaunchScreenEvent(LaunchScreenEvent.ScreenType.STATS_WEBVIEW)
        val args = Bundle()
        args.putString(StatsWebView.ARG_SPORT, sport)
        event.args = args
        EventBus.getDefault().post(event)
    }
}
