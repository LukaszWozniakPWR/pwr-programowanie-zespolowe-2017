package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

open class EmptyApiCallback : SocketAidlCallback.Stub() {

    override fun onSetNicknameResponse(success: Boolean) { /* NO-OP */ }
    override fun onConnectionLost() { /* NO-OP */ }
    override fun onPlayerList(playerList: List<Player>) { /* NO-OP */ }
    override fun onRequestGameResponse(playerAccepted: Boolean, nickname: String) { /* NO-OP */ }
    override fun onGameRequested(nickname: String) { /* NO-OP */ }

    override fun gameStartedResponse(initialGameState: GameState) { /* NO-OP */ }
    override fun opponentActionResponse(gameStateAfterOpponentMove: GameState) { /* NO-OP */ }
    override fun putCardResponse(success: Boolean, gameStateAfterYourMove: GameState) { /* NO-OP */ }
    override fun passResponse(success: Boolean, gameStateAfterYourPass: GameState) { /* NO-OP */ }
}