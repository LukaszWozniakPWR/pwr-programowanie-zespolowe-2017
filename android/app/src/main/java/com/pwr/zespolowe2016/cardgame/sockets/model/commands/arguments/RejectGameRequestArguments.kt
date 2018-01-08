package com.pwr.zespolowe2016.cardgame.sockets.model.commands.arguments

import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.commands.CommandArguments

data class RejectGameRequestArguments(
        @SerializedName("nickname") val nickname: String
) : CommandArguments()