package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

enum class RowInfo(val rowNumber: Int) { //todo add drawableRes with icon
    SWORDS(1),
    ARCHERS(2),
    CATAPULTS(3),
    ANY_OF_YOURS(4),
    ANY_OF_OPPONENTS(4),
}