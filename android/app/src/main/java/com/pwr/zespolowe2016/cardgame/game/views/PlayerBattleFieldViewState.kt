package com.pwr.zespolowe2016.cardgame.game.views

import com.pwr.zespolowe2016.cardgame.game.Card
import com.pwr.zespolowe2016.cardgame.game.views.LanesOrder.*

abstract class PlayerBattleFieldViewState(protected val playerBattleFieldView: PlayerBattleFieldView) {

    abstract fun setOnCatapultsItemClickListener(listener: (Card) -> Unit)
    abstract fun setOnSwordsItemClickListener(listener: (Card) -> Unit)
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