package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import com.google.gson.annotations.SerializedName

enum class Effect {
    @SerializedName("COMMANDERS_HORN") COMMANDERS_HORN,
    @SerializedName("BAD_WEATHER") BAD_WEATHER
}