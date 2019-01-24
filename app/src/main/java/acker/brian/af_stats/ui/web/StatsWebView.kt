package acker.brian.af_stats.ui.web

import acker.brian.af_stats.BuildConfig
import acker.brian.af_stats.R
import acker.brian.af_stats.data.model.Game
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

class StatsWebView : Fragment() {
    // Fragment that consists of just a webview for displaying stats from a static spreadsheet url
    lateinit var webView: WebView
    lateinit var sport: Game.Sport

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.stats_webview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.webview)
        val sportName = arguments?.getString(ARG_SPORT, Game.Sport.FOOTBALL.name)
        if (sportName != null) {
            sport = Game.Sport.valueOf(sportName)
        } else {
            sport = Game.Sport.FOOTBALL
        }

        when (sport) {
            Game.Sport.FOOTBALL -> webView.loadUrl(BuildConfig.FOOTBALL_SPREADSHEET_URL)
            Game.Sport.SOCCER -> webView.loadUrl(BuildConfig.SOCCER_SPREADSHEET_URL)
        }
    }

    companion object {
        const val TAG = "StatsWebViewTag"
        const val ARG_SPORT = "arg_sport"

        fun newInstance(sport: String): StatsWebView {
            val fragment = StatsWebView()
            val args = Bundle()
            args.putString(ARG_SPORT, sport)
            fragment.arguments = args
            return fragment
        }
    }
}