package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.*
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended.GameEndReason
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended.GameEndedResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestateresponse.GameStateResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.orThrow
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

class ResponseHandler(
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
        } else ResponseHandler(callbacks.plus(callback), queuedResponses.toMutableList())
    }

    fun withCallbackRemoved(callback: SocketAidlCallback): ResponseHandler {
        return ResponseHandler(callbacks.minus(callback))
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
            NICKNAME_RESPONSE -> handleSetNicknameResponse(response.setNicknameResponse.orThrow())
            PLAYER_LIST_RESPONSE -> handlePlayerListResponse(response.playerList.orThrow())
            REQUEST_GAME_RESPONSE -> handleRequestGameResponse(response.requestGameResponse.orThrow())
            REQUEST_GAME -> handleGameRequested(response.requestGame.orThrow())
            GAME_STARTED_RESPONSE -> gameStartedResponse(response.gameStartedResponse.orThrow())
            OPPONENT_ACTION_RESPONSE -> opponentActionResponse(response.opponentActionResponse.orThrow())
            PUT_CARD_RESPONSE -> putCardResponse(response.putCardResponse.orThrow())
            PASS_RESPONSE -> passResponse(response.passResponse.orThrow())
            GAME_ENDED_RESPONSE -> gameEndedResponse(response.gameEndedResponse.orThrow())
        }
    }

    private fun handlePing() {
        /* NO-OP, handled in Service */
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

    private fun gameStartedResponse(data: GameState) {
        callbacks.forEach { callback -> callback.gameStartedResponse(data) }
    }

    private fun opponentActionResponse(data: GameState) {
        callbacks.forEach { callback -> callback.opponentActionResponse(data) }
    }

    private fun putCardResponse(data: GameStateResponse) {
        callbacks.forEach { callback -> callback.putCardResponse(data.success, data.gameState) }
    }

    private fun passResponse(data: GameStateResponse) {
        callbacks.forEach { callback -> callback.passResponse(data.success, data.gameState) }
    }

    private fun gameEndedResponse(data: GameEndedResponse) {
        callbacks.forEach { callback ->
            when (data.gameEndReason) {
                GameEndReason.OPPONENT_DISCONNECTED -> callback.opponentDisconnected()
                GameEndReason.YOU_WON -> callback.youWonResponse()
                GameEndReason.YOU_LOST -> callback.youLostResponse()
            }
        }
    }
}