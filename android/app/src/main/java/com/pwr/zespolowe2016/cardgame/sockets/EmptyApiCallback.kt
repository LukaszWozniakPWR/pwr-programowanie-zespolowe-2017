package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

open class EmptyApiCallback : SocketAidlCallback.Stub() {

    override fun onSetNicknameResponse(success: Boolean) { /* NO-OP */ }
    override fun onConnectionLost() { /* NO-OP */ }
    override fun onPlayerList(playerList: List<Player>) { /* NO-OP */ }
    override fun onRequestGameResponse(playerAccepted: Boolean, nickname: String) { /* NO-OP */ }
}