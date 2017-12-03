package com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist

import com.google.gson.annotations.SerializedName

enum class PlayerStatus {
    @SerializedName("FREE") FREE,
    @SerializedName("PLAYING") PLAYING;
}