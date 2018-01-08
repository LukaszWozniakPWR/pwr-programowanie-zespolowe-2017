package com.pwr.zespolowe2016.cardgame.sockets;

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player;
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState;

interface SocketAidlCallback {

    void onSetNicknameResponse(boolean success);
    void onPlayerList(in List<Player> playerList);
    void onRequestGameResponse(boolean playerAccepted, String nickname);
    void onGameRequested(String nickname);
    void onConnectionLost();

    void gameStartedResponse(in GameState initialGameState);
    void opponentActionResponse(in GameState gameStateAfterOpponentMove);
    void putCardResponse(boolean success, in GameState gameStateAfterYourMove);
    void passResponse(boolean success, in GameState gameStateAfterYourPass);

    void youWonResponse();
    void youLostResponse();
    void opponentDisconnected();
}
