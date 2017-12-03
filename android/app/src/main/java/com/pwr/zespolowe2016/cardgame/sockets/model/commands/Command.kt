package com.pwr.zespolowe2016.cardgame.sockets.model.commands

import com.google.gson.annotations.SerializedName

data class Command(
        @SerializedName("command") val commandType: CommandType,
        @SerializedName("args") val arguments: CommandArguments
)