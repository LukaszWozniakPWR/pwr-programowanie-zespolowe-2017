package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Effect
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

    override fun setCatapultsData(catapults: List<Card>, effects: List<Effect>) {
        playerBattleFieldView.leftBattleLineView.cardList = catapults
        playerBattleFieldView.leftBattleLineView.effectsList = effects
    }

    override fun setSwordsData(swords: List<Card>, effects: List<Effect>) {
        playerBattleFieldView.rightBattleLineView.cardList = swords
        playerBattleFieldView.rightBattleLineView.effectsList = effects
    }

    override fun setCatapultsRowSelectable(listener: () -> Unit) {
        playerBattleFieldView.leftBattleLineView.setSelectable { listener() }
    }

    override fun setSwordsRowSelectable(listener: () -> Unit) {
        playerBattleFieldView.rightBattleLineView.setSelectable { listener() }
    }
}