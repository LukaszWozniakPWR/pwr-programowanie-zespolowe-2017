package com.pwr.zespolowe2016.cardgame.sockets;

import com.pwr.zespolowe2016.cardgame.sockets.SocketAidlCallback;

interface SocketAidlApi {
    void setNickname(String nickname);
    void getPlayerList();
    void requestGameWithPlayer(String nickname);
    void refuseGameRequestFrom(String nickname);

    void putCard(int indexOfCardInHand, int numberOfRow);
    void pass();

    void registerCallback(SocketAidlCallback callback);
    void unregisterCallback(SocketAidlCallback callback);
}
