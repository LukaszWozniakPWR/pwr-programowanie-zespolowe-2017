package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.StringRes

enum class Attribute(@StringRes val attributeName: Int, @StringRes val attributeDescription: Int) {
    COLD(0, 0), //TODO: add valid resources,
    RAIN(0, 0), //TODO: add valid resources,
    FOG(0, 0), //TODO: add valid resources,
    GOOD_WEATHER(0, 0), //TODO: add valid resources,
    COMMANDERS_HORN(0, 0), //TODO: add valid resources,
    HERO(0, 0), //TODO: add valid resources,
    MEDIC(0, 0), //TODO: add valid resources,
    SPY(0, 0), //TODO: add valid resources,
}