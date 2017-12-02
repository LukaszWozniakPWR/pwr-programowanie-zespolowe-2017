package com.pwr.zespolowe2016.cardgame.sockets

import android.app.Service
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import com.pwr.zespolowe2016.cardgame.sockets.model.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.ResponseType.NICKNAME_RESPONSE
import rx.Completable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class SocketService : Service() {

    private val serverPort = 4884
    private val clientSocket = Socket()
    private val subscriptions = mutableListOf<Subscription>()

    override fun onBind(intent: Intent?) = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int) = START_STICKY

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.forEach { subscription -> subscription.unsubscribe() }
    }

    override fun onCreate() {
        subscriptions.add(Completable.fromCallable { connectAndListen() }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnCompleted { stopSelf() }
                .subscribe (
                        { /* NO-OP */ },
                        { error -> handleError(error) }
                )
        )
    }

    private fun connectAndListen() {
        connectToSocket()
        listenForResponses()
    }

    private fun connectToSocket() {
        clientSocket.connect(InetSocketAddress(InetAddress.getLocalHost(), serverPort))
    }

    private fun listenForResponses() {
        val gson = Gson()
        val bufferedReader = clientSocket.getInputStream().bufferedReader()
        while (true) {
            handleResponse(gson.fromJson(bufferedReader, Response::class.java))
        }
    }

    private fun handleResponse(response: Response) {
        when (response.type) {
            NICKNAME_RESPONSE ->
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, "Error in SocketService", Toast.LENGTH_LONG).show()
        stopSelf()
    }

}