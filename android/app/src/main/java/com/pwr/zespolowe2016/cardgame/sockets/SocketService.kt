package com.pwr.zespolowe2016.cardgame.sockets

import android.app.Service
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import rx.Completable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.InetSocketAddress
import java.net.Socket

class SocketService : Service(), ServiceCallbacksContainer {

    private val serverPort = 4884
    private val serverHostname = "zespolowe.ddns.net"
    private val clientSocket = Socket()
    private val subscriptions = mutableListOf<Subscription>()
    private var responseHandler = ResponseHandler()
    private val binder: SocketAidlApi.Stub = SocketAidlApiImpl(clientSocket, this)

    override fun onBind(intent: Intent?) = binder
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        subscriptions.add(
                Completable.fromCallable { connectAndListen() }
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnCompleted { stopSelf() }
                        .subscribe (
                                { /* NO-OP */ },
                                { error -> handleError(error) }
                        )
        )
        return START_STICKY
    }

    override fun registerCallback(callback: SocketAidlCallback) {
        responseHandler = responseHandler.withCallbackAdded(callback)
    }

    override fun unregisterCallback(callback: SocketAidlCallback) {
        responseHandler = responseHandler.withCallbackRemoved(callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        subscriptions.forEach { subscription -> subscription.unsubscribe() }
    }

    private fun connectAndListen() {
        connectToSocket()
        listenForResponses()
    }

    private fun connectToSocket() {
        clientSocket.connect(InetSocketAddress(serverHostname, serverPort))
    }

    private fun listenForResponses() {
        val gson = Gson()
        val jsonReader = gson.newJsonReader(clientSocket.getInputStream().bufferedReader())
        while (true) {
            val response = gson.fromJson<Response>(jsonReader, Response::class.java)
            Log.v("Received response", response.toString())
            subscriptions.add(
                    Completable.fromCallable { responseHandler.handleResponse(response) }
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe { /* NO-OP */ }
            )
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, "Error in SocketService", Toast.LENGTH_LONG).show()
        stopSelf()
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SocketService::class.java)
        }
    }
}