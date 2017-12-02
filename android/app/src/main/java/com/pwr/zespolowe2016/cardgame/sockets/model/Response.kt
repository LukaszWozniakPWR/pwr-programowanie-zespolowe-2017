package com.pwr.zespolowe2016.cardgame.sockets.model

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("type") val type: ResponseType,
        @SerializedName("response") val response: Any
)