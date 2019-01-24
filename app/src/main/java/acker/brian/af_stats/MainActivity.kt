package acker.brian.af_stats

import acker.brian.af_stats.event.LaunchScreenEvent
import acker.brian.af_stats.ui.home.HomeFragment
import acker.brian.af_stats.ui.web.StatsWebView
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this@MainActivity)) {
            EventBus.getDefault().register(this@MainActivity)
        }
    }

    override fun onStop() {
        super.onStop()
        if (EventBus.getDefault().isRegistered(this@MainActivity)) {
            EventBus.getDefault().unregister(this@MainActivity)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLaunchScreenEvent(event: LaunchScreenEvent) {
        when (event.screenType) {
            LaunchScreenEvent.ScreenType.STATS_WEBVIEW -> {
                val sport = event.args?.getString(StatsWebView.ARG_SPORT)
                if (sport != null) {
                    addFragment(StatsWebView.newInstance(sport = sport), StatsWebView.TAG)
                } else {
                    Timber.e("StatsWebView must have sport argument")
                    Toast.makeText(this@MainActivity, "StatsWebview must have sport argument", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
                .add(fragment, tag)
                .commitAllowingStateLoss()
    }
}
