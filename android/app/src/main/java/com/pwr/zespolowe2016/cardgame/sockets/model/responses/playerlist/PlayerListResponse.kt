package com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist

import com.google.gson.annotations.SerializedName

data class PlayerListResponse(
        @SerializedName("players") val playerList: List<Player>
) {
    companion object {
        fun empty() = PlayerListResponse(
                emptyList()
        )
    }
}