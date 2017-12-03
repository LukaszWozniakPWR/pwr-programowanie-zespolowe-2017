package com.pwr.zespolowe2016.cardgame.sockets;

import com.pwr.zespolowe2016.cardgame.sockets.SocketAidlCallback;

interface SocketAidlApi {
    void setNickname(String nickname);

    void registerCallback(SocketAidlCallback callback);
    void unregisterCallback(SocketAidlCallback callback);
}
