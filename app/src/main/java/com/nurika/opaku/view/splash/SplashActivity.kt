package com.nurika.opaku.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.nurika.opaku.databinding.ActivitySplashBinding
import com.nurika.opaku.view.BaseActivity
import com.nurika.opaku.view.home.HomeActivity

class SplashActivity : BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenName = "Splash Screen"

        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }

    override fun onStart() {
        super.onStart()

        sendLog()
        sendEventAppOpen()
    }

}