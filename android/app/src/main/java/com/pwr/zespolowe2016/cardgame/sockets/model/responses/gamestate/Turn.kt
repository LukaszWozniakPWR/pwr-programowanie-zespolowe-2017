package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import com.google.gson.annotations.SerializedName

enum class Turn {
    @SerializedName("YOUR") YOUR,
    @SerializedName("OPPONENT") OPPONENT
}