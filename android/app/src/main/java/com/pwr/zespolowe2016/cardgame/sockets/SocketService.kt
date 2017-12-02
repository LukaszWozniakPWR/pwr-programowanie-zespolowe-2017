package com.pwr.zespolowe2016.cardgame.sockets

import android.app.Service
import android.content.Intent
import android.widget.Toast
import rx.Completable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class SocketService : Service() {

    private val serverPort = 4884
    private val clientSocket = Socket()

    override fun onBind(intent: Intent?) = null

    override fun onCreate() {
        Completable.fromCallable { connectAndListen() }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted { stopSelf() }
                .subscribe (
                        { /* NO-OP */ },
                        { error -> handleError(error) }
                )
    }

    private fun connectAndListen() {
        connectToSocket()

    }

    private fun connectToSocket() {
        clientSocket.connect(InetSocketAddress(InetAddress.getLocalHost(), serverPort))
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, "Error in SocketService", Toast.LENGTH_LONG).show()
        stopSelf()
    }

}