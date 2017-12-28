package com.pwr.zespolowe2016.cardgame.sockets

import android.app.Service
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.PING_FROM_SERVER
import rx.Completable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.Socket
import java.util.concurrent.TimeUnit.MILLISECONDS

class SocketService : Service(), ServiceCallbacksContainer {

    private val serverPort = 4884
    private val serverHostname = "zespolowe.ddns.net"
    private var clientSocket = Socket()
    private val listenScheduler = Schedulers.newThread()
    private val subscriptions = mutableListOf<Subscription>()
    private val binder = SocketAidlApiImpl(clientSocket, this)
    private var responseHandler = ResponseHandler()

    override fun onBind(intent: Intent?) = binder
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Log.d("SocketService", "onStartCommand")
        connectAndListenOnListenThread()
        return START_NOT_STICKY
    }

    private fun connectAndListenOnListenThread() {
        subscriptions.add(
                Completable.fromCallable { connectAndListen() }
                        .subscribeOn(listenScheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe (
                                { /* NO-OP */ },
                                { error -> handleError(error) }
                        )
        )
    }

    override fun registerCallback(callback: SocketAidlCallback) {
        responseHandler = responseHandler.withCallbackAdded(callback)
    }

    override fun unregisterCallback(callback: SocketAidlCallback) {
        responseHandler = responseHandler.withCallbackRemoved(callback)
    }

    override fun notifyConnectionLost() {
        unsubscribeAll()
        responseHandler.notifyConnectionLost()
        connectAndListenOnListenThread()
    }

    override fun onDestroy() {
        Log.d("SocketService", "onDestroy")
        super.onDestroy()
        stopSelf()
        unsubscribeAll()
    }

    private fun unsubscribeAll() {
        subscriptions.forEach { subscription -> subscription.unsubscribe() }
        subscriptions.clear()
    }

    private fun connectAndListen() {
        connectToSocket()
        listenForResponses()
    }

    private fun connectToSocket() {
        clientSocket = Socket(serverHostname, serverPort)
        binder.socket = clientSocket
    }

    private fun listenForResponses() {
        val gson = Gson()
        val jsonReader = gson.newJsonReader(clientSocket.getInputStream().bufferedReader())
        while (true) {
            val response = gson.fromJson<Response>(jsonReader, Response::class.java)
            Log.v("Received response", response.toString())
            if (response.type == PING_FROM_SERVER) binder.answerPingWithPong()
            subscriptions.add(
                    Completable.fromCallable { responseHandler.handleResponse(response) }
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe { /* NO-OP */ }
            )
        }
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
        clientSocket.close()
        responseHandler.notifyConnectionLost()
        Completable.timer(CONNECTION_RETRY_DELAY, MILLISECONDS)
                .subscribeOn(listenScheduler)
                .observeOn(listenScheduler)
                .subscribe { connectAndListenOnListenThread() }
    }

    companion object {
        private const val CONNECTION_RETRY_DELAY = 500L
        fun getIntent(context: Context): Intent {
            return Intent(context, SocketService::class.java)
        }
    }
}