package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card
import kotlinx.android.synthetic.main.player_battle_field_view.view.rightBattleLine

class CatapultsFirstBattleFieldViewState(
        playerBattleFieldView: PlayerBattleFieldView
) : PlayerBattleFieldViewState(playerBattleFieldView) {

    override fun setOnCatapultsItemClickListener(listener: (Int, Card) -> Unit) {
        playerBattleFieldView.leftBattleLineView.onCardClickListener = listener
    }

    override fun setOnSwordsItemClickListener(listener: (Int, Card) -> Unit) {
        playerBattleFieldView.rightBattleLineView.onCardClickListener = listener
    }

    override fun setCatapultsData(catapults: List<Card>) {
        playerBattleFieldView.leftBattleLineView.cardList = catapults
    }

    override fun setSwordsData(swords: List<Card>) {
        playerBattleFieldView.rightBattleLineView.cardList = swords
    }

    override fun setOnCatapultsRowClickListener(listener: () -> Unit) {
        playerBattleFieldView.rightBattleLine.setOnClickListener { listener() }
    }

    override fun setOnSwordsRowClickListener(listener: () -> Unit) {
        playerBattleFieldView.leftBattleLineView.setOnClickListener { listener() }
    }
}