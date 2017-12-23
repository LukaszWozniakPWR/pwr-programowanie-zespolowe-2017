package com.pwr.zespolowe2016.cardgame.sockets.model.commands

import com.google.gson.annotations.SerializedName

enum class CommandType {
    @SerializedName("Pong") PONG_COMMAND,
    @SerializedName("set_nickname") SET_NICKNAME_COMMAND,
    @SerializedName("get_players") GET_PLAYER_LIST_COMMAND,
    @SerializedName("request_game") REQUEST_GAME_COMMAND,
    @SerializedName("reject_request_game") REJECT_GAME_REQUEST_COMMAND
}