package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.game.views.battle_view.LanesOrder.*
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

abstract class PlayerBattleFieldViewState(protected val playerBattleFieldView: PlayerBattleFieldView) {

    abstract fun setOnCatapultsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setOnSwordsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setCatapultsData(catapults: List<Card>)
    abstract fun setSwordsData(swords: List<Card>)

    companion object {
        fun fromLanesOrder(
                lanesOrder: LanesOrder,
                playerBattleFieldView: PlayerBattleFieldView
        ): PlayerBattleFieldViewState {
            return when(lanesOrder) {
                CATAPULS_FIRST -> CatapultsFirstBattleFieldViewState(playerBattleFieldView)
                SWORDS_FIRST -> SwordsFirstBattleFieldViewState(playerBattleFieldView)
            }
        }
    }
}