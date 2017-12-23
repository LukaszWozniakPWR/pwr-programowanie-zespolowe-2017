package com.pwr.zespolowe2016.cardgame.game.views

import com.pwr.zespolowe2016.cardgame.game.Card

class CatapultsFirstBattleFieldViewState(
        playerBattleFieldView: PlayerBattleFieldView
) : PlayerBattleFieldViewState(playerBattleFieldView) {

    override fun setOnCatapultsItemClickListener(listener: (Card) -> Unit) {
        playerBattleFieldView.leftBattleLineView.onCardClickListener = listener
    }

    override fun setOnSwordsItemClickListener(listener: (Card) -> Unit) {
        playerBattleFieldView.rightBattleLineView.onCardClickListener = listener
    }

    override fun setCatapultsData(catapults: List<Card>) {
        playerBattleFieldView.leftBattleLineView.setData(catapults)
    }

    override fun setSwordsData(swords: List<Card>) {
        playerBattleFieldView.rightBattleLineView.setData(swords)
    }
}