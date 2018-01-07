package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended.GameEndedResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestateresponse.GameStateResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

data class Response(
        @SerializedName("type") val type: ResponseType,
        @SerializedName("SetNicknameResponse") val setNicknameResponse: SetNicknameResponse?,
        @SerializedName("PlayerList") val playerList: PlayerListResponse?,
        @SerializedName("RequestGameResponse") val requestGameResponse: RequestGameResponse?,
        @SerializedName("RequestGame") val requestGame: RequestGame?,
        @SerializedName("GameStartedResponse") val gameStartedResponse: GameState?,
        @SerializedName("OpponentActionResponse") val opponentActionResponse: GameState?,
        @SerializedName("PutCardResponse") val putCardResponse: GameStateResponse?,
        @SerializedName("PassResponse") val passResponse: GameStateResponse?,
        @SerializedName("GameEndedResponse") val gameEndedResponse: GameEndedResponse?
)