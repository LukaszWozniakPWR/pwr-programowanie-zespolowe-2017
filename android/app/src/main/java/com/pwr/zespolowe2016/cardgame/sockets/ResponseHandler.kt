package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.NICKNAME_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.PLAYER_LIST_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.REQUEST_GAME
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.REQUEST_GAME_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.orEmpty
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

class ResponseHandler(private val callbacks: List<SocketAidlCallback> = emptyList()) {

    fun withCallbackAdded(callback: SocketAidlCallback): ResponseHandler {
        return if (callbacks.contains(callback)) this else ResponseHandler(callbacks.plus(callback))
    }

    fun withCallbackRemoved(callback: SocketAidlCallback): ResponseHandler {
        return ResponseHandler(callbacks.minus(callback))
    }

    fun notifyConnectionLost() {
        callbacks.forEach { callback -> callback.onConnectionLost() }
    }

    fun handleResponse(response: Response) {
        when (response.type) {
            NICKNAME_RESPONSE -> handleSetNicknameResponse(response.setNicknameResponse.orEmpty())
            PLAYER_LIST_RESPONSE -> handlePlayerListResponse(response.playerList.orEmpty())
            REQUEST_GAME_RESPONSE -> handleRequestGameResponse(response.requestGameResponse.orEmpty())
            REQUEST_GAME -> handleGameRequested(response.requestGame.orEmpty())
        }
    }

    private fun handleSetNicknameResponse(data: SetNicknameResponse) {
        callbacks.forEach { callback -> callback.onSetNicknameResponse(data.success) }
    }

    private fun handlePlayerListResponse(data: PlayerListResponse) {
        callbacks.forEach { callback -> callback.onPlayerList(data.playerList) }
    }

    private fun handleRequestGameResponse(data: RequestGameResponse) {
        callbacks.forEach { callback ->
            callback.onRequestGameResponse(data.playerAccepted, data.nickname)
        }
    }

    private fun handleGameRequested(data: RequestGame) {
        callbacks.forEach { callback -> callback.onGameRequested(data.nickname) }
    }
}