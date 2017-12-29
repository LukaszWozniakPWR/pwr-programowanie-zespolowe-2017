package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class SwordsFirstBattleFieldViewState(
        playerBattleFieldView: PlayerBattleFieldView
) : PlayerBattleFieldViewState(playerBattleFieldView) {

    override fun setOnCatapultsItemClickListener(listener: (Int, Card) -> Unit) {
        playerBattleFieldView.rightBattleLineView.onCardClickListener = listener
    }

    override fun setOnSwordsItemClickListener(listener: (Int, Card) -> Unit) {
        playerBattleFieldView.leftBattleLineView.onCardClickListener = listener
    }

    override fun setCatapultsData(catapults: List<Card>) {
        playerBattleFieldView.rightBattleLineView.cardList = catapults
    }

    override fun setSwordsData(swords: List<Card>) {
        playerBattleFieldView.leftBattleLineView.cardList = swords
    }
}