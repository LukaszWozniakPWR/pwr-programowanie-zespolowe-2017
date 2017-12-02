package com.pwr.zespolowe2016.cardgame.other


import android.app.Activity
import android.content.Intent

class Navigation(private val context: Activity) {

    private fun startActivity(intent: Intent) {
        context.startActivity(intent)
    }

    private fun startActivityForResult(intent: Intent, requestCode: Int) {
        context.startActivityForResult(intent, requestCode)
    }
}