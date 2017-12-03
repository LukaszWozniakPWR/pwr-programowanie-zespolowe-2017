package com.pwr.zespolowe2016.cardgame.sockets.model.commands

import com.google.gson.annotations.SerializedName

data class RequestGameArguments(
        @SerializedName("nickname") val nickname: String
) : CommandArguments()