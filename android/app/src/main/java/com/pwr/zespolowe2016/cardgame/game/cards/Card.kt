package com.pwr.zespolowe2016.cardgame.game.cards

data class Card(val points: Int,
                val name: String,
                val photo: Int,
                val type: CardType,
                val description: String)