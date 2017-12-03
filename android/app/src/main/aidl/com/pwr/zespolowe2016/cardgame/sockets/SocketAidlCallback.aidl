package com.pwr.zespolowe2016.cardgame.sockets;

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player;

interface SocketAidlCallback {

    void onSetNicknameResponse(boolean success);
    void onPlayerList(in List<Player> playerList);
    void onConnectionLost();
}
