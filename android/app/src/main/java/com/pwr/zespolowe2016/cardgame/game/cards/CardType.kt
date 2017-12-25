package com.pwr.zespolowe2016.cardgame.game.cards

enum class CardType {
    // TODO real types
    ONE {
        override fun realName() = "TYPE ONE"
    },
    TWO {
        override fun realName() = "TYPE TWO"
    };

    abstract fun realName(): String
}