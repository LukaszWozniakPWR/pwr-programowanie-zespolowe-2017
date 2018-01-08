package com.pwr.zespolowe2016.cardgame.game.views.battle_view

enum class LanesOrder(val value: Int) {
    CATAPULS_FIRST(0),
    SWORDS_FIRST(1);

    companion object {
        fun fromInt(value: Int): LanesOrder {
            return when (value) {
                CATAPULS_FIRST.value -> CATAPULS_FIRST
                SWORDS_FIRST.value -> SWORDS_FIRST
                else -> throw IllegalStateException("Wrong Lanes Order!")
            }
        }
    }
}