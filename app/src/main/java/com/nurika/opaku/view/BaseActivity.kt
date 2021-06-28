package com.nurika.opaku.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics
    protected lateinit var screenName: String

    override fun onStart() {
        firebaseAnalytics = Firebase.analytics

        super.onStart()

    }

    protected fun sendLog() {
        Log.d(screenName, "Sending data analytics")
        val parameters = Bundle().apply {
            this.putString("screen_name", screenName)
        }
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, parameters)
    }

    protected fun sendEventAppOpen(){
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)
    }
}