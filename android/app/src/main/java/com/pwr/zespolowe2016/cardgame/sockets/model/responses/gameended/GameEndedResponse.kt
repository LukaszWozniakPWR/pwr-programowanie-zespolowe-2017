package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended

import com.google.gson.annotations.SerializedName

data class GameEndedResponse(
        @SerializedName("reason") val gameEndReason: GameEndReason
)