package com.pwr.zespolowe2016.cardgame.sockets.model.commands

import com.google.gson.annotations.SerializedName

data class RejectGameRequestArguments(
        @SerializedName("nickname") val nickname: String
) : CommandArguments()