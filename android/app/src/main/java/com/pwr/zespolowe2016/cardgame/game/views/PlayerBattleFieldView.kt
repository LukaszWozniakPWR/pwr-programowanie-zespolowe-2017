package com.pwr.zespolowe2016.cardgame.game.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.R.layout
import com.pwr.zespolowe2016.cardgame.game.Card
import com.pwr.zespolowe2016.cardgame.game.views.LanesOrder.CATAPULS_FIRST
import com.pwr.zespolowe2016.cardgame.game.views.PlayerBattleFieldViewState.Companion
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.extensions.extractAttributes

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
        middleBattleLineView.setData(archers)
    }

    fun setSwordsData(swords: List<Card>) {
        state.setSwordsData(swords)
    }

    init {
        inflate(context, R.layout.player_battle_field_view, this)
        attrs?.extractAttributes(context, R.styleable.PlayerBattleFieldView) { typedArray ->
            lanesOrder = LanesOrder.fromInt(
                    typedArray.getInt(R.styleable.PlayerBattleFieldView_lanesOrder, lanesOrder.value)
            )
        }
    }
}