package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgame.RequestGame
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.requestgameresponse.RequestGameResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname.SetNicknameResponse

fun PlayerListResponse?.orEmpty() = this ?: PlayerListResponse.empty()
fun SetNicknameResponse?.orEmpty() = this ?: SetNicknameResponse.empty()
fun RequestGameResponse?.orEmpty() = this ?: RequestGameResponse.empty()
fun RequestGame?.orEmpty() = this ?: RequestGame.empty()