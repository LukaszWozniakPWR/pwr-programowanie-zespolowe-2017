package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.NICKNAME_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.PING_FROM_SERVER
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.PLAYER_LIST_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.REQUEST_GAME
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.REQUEST_GAME_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.orEmpty
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

class ResponseHandler(
        private val socketApi: SocketAidlApiImpl,
        private val callbacks: List<SocketAidlCallback> = emptyList(),
        private val queuedResponses: MutableList<Response> = mutableListOf(),
        private var queuedConnectionLost: Boolean = false
) {

    init {
        if (callbacks.isNotEmpty()) {
            if (queuedConnectionLost) {
                queuedResponses.clear()
                notifyConnectionLost()
                queuedConnectionLost = false
            } else {
                queuedResponses.reversed().forEach { response -> handleResponse(response) }
                queuedResponses.clear()
            }
        }
    }

    fun withCallbackAdded(callback: SocketAidlCallback): ResponseHandler {
        return if (callbacks.contains(callback)) {
            this
        } else ResponseHandler(socketApi, callbacks.plus(callback), queuedResponses.toMutableList())
    }

    fun withCallbackRemoved(callback: SocketAidlCallback): ResponseHandler {
        return ResponseHandler(socketApi, callbacks.minus(callback))
    }

    fun notifyConnectionLost() {
        if (callbacks.isEmpty()) {
            queuedConnectionLost = true
            return
        }
        callbacks.forEach { callback -> callback.onConnectionLost() }
    }

    fun handleResponse(response: Response) {
        if (callbacks.isEmpty()) {
            queuedResponses.add(response)
            return
        }
        when (response.type) {
            PING_FROM_SERVER -> handlePing()
            NICKNAME_RESPONSE -> handleSetNicknameResponse(response.setNicknameResponse.orEmpty())
            PLAYER_LIST_RESPONSE -> handlePlayerListResponse(response.playerList.orEmpty())
            REQUEST_GAME_RESPONSE -> handleRequestGameResponse(response.requestGameResponse.orEmpty())
            REQUEST_GAME -> handleGameRequested(response.requestGame.orEmpty())
        }
    }

    private fun handlePing() {
        socketApi.answerPingWithPong()
    }

    private fun handleSetNicknameResponse(data: SetNicknameResponse) {
        callbacks.forEach { callback -> callback.onSetNicknameResponse(data.success) }
    }

    private fun handlePlayerListResponse(data: PlayerListResponse) {
        callbacks.forEach { callback -> callback.onPlayerList(data.playerList) }
    }

    private fun handleRequestGameResponse(data: RequestGameResponse) {
        callbacks.forEach { callback -> callback.onRequestGameResponse(data.playerAccepted, data.nickname) }
    }

    private fun handleGameRequested(data: RequestGame) {
        callbacks.forEach { callback -> callback.onGameRequested(data.nickname) }
    }
}