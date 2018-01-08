package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

enum class RowInfo(val rowNumber: Int, val realName: String) { //todo add drawableRes with icon
    SWORDS(1, "Miecznik"),
    ARCHERS(2, "Łucznik"),
    CATAPULTS(3, "Katapulta"),
    ANY_OF_YOURS(4, "NI MO"),
    ANY_OF_OPPONENTS(4, " TEZ NI MO")
}