package com.pwr.zespolowe2016.cardgame.application

import android.app.Application
import com.pwr.zespolowe2016.cardgame.sockets.SocketService

class CardGameApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startService(SocketService.getIntent(this))
    }
}