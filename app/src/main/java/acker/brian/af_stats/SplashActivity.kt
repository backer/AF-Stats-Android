package acker.brian.af_stats

import android.content.Intent
import android.os.Bundle
import android.os.Handler
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
        val runnable = Runnable {
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
        Handler().postDelayed(runnable, 3000)
    }
}