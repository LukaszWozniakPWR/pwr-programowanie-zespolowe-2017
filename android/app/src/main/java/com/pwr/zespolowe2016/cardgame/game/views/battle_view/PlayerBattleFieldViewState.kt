package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.game.views.battle_view.LanesOrder.*
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card
import kotlinx.android.synthetic.main.player_battle_field_view.view.middleBattleLine

abstract class PlayerBattleFieldViewState(protected val playerBattleFieldView: PlayerBattleFieldView) {

    abstract fun setOnCatapultsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setOnSwordsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setCatapultsData(catapults: List<Card>)
    abstract fun setSwordsData(swords: List<Card>)

    abstract fun setOnCatapultsRowClickListener(listener: () -> Unit)

    fun setOnArchersRowClickListener(listener: () -> Unit) {
        playerBattleFieldView.middleBattleLine.setOnClickListener { listener() }
    }

    abstract fun setOnSwordsRowClickListener(listener: () -> Unit)

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