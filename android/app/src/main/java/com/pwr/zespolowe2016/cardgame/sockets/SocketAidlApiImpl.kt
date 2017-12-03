package com.pwr.zespolowe2016.cardgame.sockets

import android.util.Log
import com.google.gson.Gson
import com.google.gson.stream.JsonWriter
import com.pwr.zespolowe2016.cardgame.sockets.model.commands.Command
import com.pwr.zespolowe2016.cardgame.sockets.model.commands.CommandType.SET_NICKNAME_COMMAND
import com.pwr.zespolowe2016.cardgame.sockets.model.commands.SetNicknameArguments
import rx.Completable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.Socket

class SocketAidlApiImpl(
        private val socket: Socket,
        private val serviceCallbacksContainer: ServiceCallbacksContainer
) : SocketAidlApi.Stub() {

    private val gson = Gson()

    override fun registerCallback(callback: SocketAidlCallback) {
        serviceCallbacksContainer.registerCallback(callback)
    }

    override fun unregisterCallback(callback: SocketAidlCallback) {
        serviceCallbacksContainer.unregisterCallback(callback)
    }

    override fun setNickname(nickname: String) {
        asyncSendCommand(Command(SET_NICKNAME_COMMAND, SetNicknameArguments(nickname)))
    }

    private fun asyncSendCommand(command: Command) {
        Completable.fromCallable { sendCommand(command) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        { /* NO-OP */ },
                        { error -> handleError(error) }
                )
    }

    private fun handleError(error: Throwable) {
        error.printStackTrace()
    }

    private fun sendCommand(command: Command) {
        val bufferedWriter = socket.getOutputStream().bufferedWriter()
        val jsonWriter = JsonWriter(bufferedWriter)
        val jsonTree = gson.toJsonTree(command)
        Log.v("Sending command: 123",jsonTree.toString())
        gson.toJson(jsonTree, jsonWriter)
        bufferedWriter.newLine()
        jsonWriter.flush()
    }
}