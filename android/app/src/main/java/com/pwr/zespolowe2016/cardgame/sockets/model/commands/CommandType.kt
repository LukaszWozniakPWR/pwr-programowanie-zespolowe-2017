package com.pwr.zespolowe2016.cardgame.sockets.model.commands

import com.google.gson.annotations.SerializedName

enum class CommandType {
    @SerializedName("set_nickname") SET_NICKNAME_COMMAND,
    @SerializedName("get_players") GET_PLAYER_LIST_COMMAND
}