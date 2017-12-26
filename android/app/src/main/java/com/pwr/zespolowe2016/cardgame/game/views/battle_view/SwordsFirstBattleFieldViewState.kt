package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.game.cards.Card

class SwordsFirstBattleFieldViewState(
        playerBattleFieldView: PlayerBattleFieldView
) : PlayerBattleFieldViewState(playerBattleFieldView) {

    override fun setOnCatapultsItemClickListener(listener: (Card) -> Unit) {
        playerBattleFieldView.rightBattleLineView.onCardClickListener = listener
    }

    override fun setOnSwordsItemClickListener(listener: (Card) -> Unit) {
        playerBattleFieldView.leftBattleLineView.onCardClickListener = listener
    }

    override fun setCatapultsData(catapults: List<Card>) {
        playerBattleFieldView.rightBattleLineView.cardList = catapults
    }

    override fun setSwordsData(swords: List<Card>) {
        playerBattleFieldView.leftBattleLineView.cardList = swords
    }
}