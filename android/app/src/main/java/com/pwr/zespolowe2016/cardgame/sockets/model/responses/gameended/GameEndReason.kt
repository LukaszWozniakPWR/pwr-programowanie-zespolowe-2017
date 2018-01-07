package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended

import com.google.gson.annotations.SerializedName

enum class GameEndReason {
    @SerializedName("OPPONENT_DISCONNECTED") OPPONENT_DISCONNECTED,
    @SerializedName("WON") YOU_WON,
    @SerializedName("LOST") YOU_LOST,
}