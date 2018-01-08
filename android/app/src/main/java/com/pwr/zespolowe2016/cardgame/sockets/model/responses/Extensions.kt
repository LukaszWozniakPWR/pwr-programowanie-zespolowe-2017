package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended.GameEndReason
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gameended.GameEndedResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestateresponse.GameStateResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

fun PlayerListResponse?.orThrow() = this ?: throw IllegalArgumentException("PlayerListResponse came empty from server")
fun SetNicknameResponse?.orThrow() = this ?: throw IllegalArgumentException("SetNicknameResponse came empty from server")
fun RequestGameResponse?.orThrow() = this ?: throw IllegalArgumentException("RequestGameResponse came empty from server")
fun RequestGame?.orThrow() = this ?: throw IllegalArgumentException("RequestGame came empty from server")
fun GameState?.orThrow() = this ?: throw IllegalArgumentException("GameState came empty from server")
fun GameStateResponse?.orThrow() = this ?: throw IllegalArgumentException("GameStateResponse came empty from server")
fun GameEndedResponse?.orThrow() = this ?: throw IllegalArgumentException("GameEndedResponse came empty from server")