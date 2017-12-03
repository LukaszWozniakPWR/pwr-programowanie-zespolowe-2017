package com.pwr.zespolowe2016.cardgame.sockets

interface ServiceCallbacksContainer {
    fun registerCallback(callback: SocketAidlCallback)
    fun unregisterCallback(callback: SocketAidlCallback)
}