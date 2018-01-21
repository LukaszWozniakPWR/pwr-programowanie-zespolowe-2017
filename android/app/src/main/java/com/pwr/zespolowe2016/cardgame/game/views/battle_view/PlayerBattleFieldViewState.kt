package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import com.pwr.zespolowe2016.cardgame.game.views.battle_view.LanesOrder.*
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Effect
import kotlinx.android.synthetic.main.player_battle_field_view.view.middleBattleLine

abstract class PlayerBattleFieldViewState(protected val playerBattleFieldView: PlayerBattleFieldView) {

    abstract fun setOnCatapultsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setOnSwordsItemClickListener(listener: (Int, Card) -> Unit)
    abstract fun setCatapultsData(catapults: List<Card>, effects: List<Effect>)
    abstract fun setSwordsData(swords: List<Card>, effects: List<Effect>)

    abstract fun setCatapultsRowSelectable(listener: () -> Unit)

    fun setArchersRowSelectable(listener: () -> Unit) {
        playerBattleFieldView.middleBattleLine.setSelectable { listener() }
    }

    abstract fun setSwordsRowSelectable(listener: () -> Unit)

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