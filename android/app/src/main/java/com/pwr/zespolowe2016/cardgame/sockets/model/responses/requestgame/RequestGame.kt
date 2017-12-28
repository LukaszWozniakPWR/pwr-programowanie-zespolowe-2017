package com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame

import com.google.gson.annotations.SerializedName

data class RequestGame(
        @SerializedName("nickname") val nickname: String
)