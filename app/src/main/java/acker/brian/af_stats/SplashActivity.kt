package acker.brian.af_stats

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
    }

    override fun onStart() {
        super.onStart()
        // todo make any necessary network calls/configuration here
        // until then, just show splash for 3 seconds and then transition to MainActivity
    }
}