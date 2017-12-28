package com.pwr.zespolowe2016.cardgame.application

import android.app.Application
import android.content.Intent
import android.content.ServiceConnection
import com.pwr.zespolowe2016.cardgame.sockets.SocketService

class CardGameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startService(SocketService.getIntent(this))
    }
}