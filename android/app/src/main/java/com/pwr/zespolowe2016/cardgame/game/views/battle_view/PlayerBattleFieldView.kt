package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.R.styleable
import com.pwr.zespolowe2016.cardgame.game.views.battle_view.LanesOrder.CATAPULS_FIRST
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.extensions.extractAttributes
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class PlayerBattleFieldView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val leftBattleLineView: BattleLineView by bindView(R.id.leftBattleLine)
    private val middleBattleLineView: BattleLineView by bindView(R.id.middleBattleLine)
    val rightBattleLineView: BattleLineView by bindView(R.id.rightBattleLine)

    var lanesOrder: LanesOrder = CATAPULS_FIRST
        set(value) {
            state = PlayerBattleFieldViewState.fromLanesOrder(value, this)
            field = value
        }

    private var state = PlayerBattleFieldViewState.fromLanesOrder(lanesOrder, this)

    var onCatapultsItemClickListener: (Card) -> Unit = { /* NO-OP */ }
        set(value) {
            state.setOnCatapultsItemClickListener(value)
            field = value
        }
    var onArchersItemClickListener: (Card) -> Unit = { /* NO-OP */ }
        set(value) {
            middleBattleLineView.onCardClickListener = value
            field = value
        }
    var onSwordsItemClickListener: (Card) -> Unit = { /* NO-OP */ }
        set(value) {
            state.setOnSwordsItemClickListener(value)
            field = value
        }

    fun setCatapultsData(catapults: List<Card>) {
        state.setCatapultsData(catapults)
    }

    fun setArchersData(archers: List<Card>) {
        middleBattleLineView.cardList = archers
    }

    fun setSwordsData(swords: List<Card>) {
        state.setSwordsData(swords)
    }

    init {
        inflate(context, R.layout.player_battle_field_view, this)
        attrs?.extractAttributes(context, R.styleable.PlayerBattleFieldView) { typedArray ->
            lanesOrder = LanesOrder.fromInt(
                    typedArray.getInt(styleable.PlayerBattleFieldView_lanesOrder, lanesOrder.value)
            )
        }
    }
}