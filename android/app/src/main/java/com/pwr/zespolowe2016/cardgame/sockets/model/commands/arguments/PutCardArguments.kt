package com.pwr.zespolowe2016.cardgame.sockets.model.commands.arguments

import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.commands.CommandArguments

data class PutCardArguments(
        @SerializedName("cardNumber") val indexOfCardInHand: Int,
        @SerializedName("row") val numberOfRow: Int
) : CommandArguments()