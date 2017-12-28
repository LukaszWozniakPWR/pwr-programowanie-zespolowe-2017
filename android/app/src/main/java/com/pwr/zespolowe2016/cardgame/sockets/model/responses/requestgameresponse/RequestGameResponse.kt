package com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse

import com.google.gson.annotations.SerializedName

data class RequestGameResponse(
        @SerializedName("success") val playerAccepted: Boolean,
        @SerializedName("nickname") val nickname: String
)