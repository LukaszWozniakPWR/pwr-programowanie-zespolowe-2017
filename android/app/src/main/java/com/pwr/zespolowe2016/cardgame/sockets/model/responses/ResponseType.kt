package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.google.gson.annotations.SerializedName

enum class ResponseType {
    @SerializedName("Ping") PING_FROM_SERVER,
    @SerializedName("SetNicknameResponse") NICKNAME_RESPONSE,
    @SerializedName("PlayerList") PLAYER_LIST_RESPONSE,
    @SerializedName("RequestGameResponse") REQUEST_GAME_RESPONSE,
    @SerializedName("RequestGame") REQUEST_GAME
}