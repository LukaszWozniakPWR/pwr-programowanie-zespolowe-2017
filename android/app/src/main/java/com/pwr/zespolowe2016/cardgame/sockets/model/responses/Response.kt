package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

data class Response(
        @SerializedName("type") val type: ResponseType,
        @SerializedName("SetNicknameResponse") val setNicknameResponse: SetNicknameResponse?,
        @SerializedName("PlayerList") val playerList: PlayerListResponse?,
        @SerializedName("RequestGameResponse") val requestGameResponse: RequestGameResponse?
)