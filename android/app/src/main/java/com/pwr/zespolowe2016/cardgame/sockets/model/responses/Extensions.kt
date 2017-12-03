package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerListResponse
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.set_nickname.SetNicknameResponse

fun PlayerListResponse?.orEmpty() = this ?: PlayerListResponse.empty()
fun SetNicknameResponse?.orEmpty() = this ?: SetNicknameResponse.empty()