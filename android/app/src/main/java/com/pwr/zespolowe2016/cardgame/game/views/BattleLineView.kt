package com.pwr.zespolowe2016.cardgame.game.views

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.LayoutDirection
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.R.layout
import com.pwr.zespolowe2016.cardgame.game.Card
import com.pwr.zespolowe2016.cardgame.game.CardsAdapter
import com.pwr.zespolowe2016.cardgame.other.bindString
import com.pwr.zespolowe2016.cardgame.other.bindView

class BattleLineView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var totalPoints: Int = 0
        set(value) {
            totalPointsView.text = value.toString()
            field = value
        }

    private val recyclerView: RecyclerView by bindView(R.id.recyclerView)
    private val totalPointsView: TextView by bindView(R.id.totalPoints)

    private val cardsAdapter: CardsAdapter = CardsAdapter()

    var onCardClickListener: (Card) -> Unit = cardsAdapter.onItemClickListener
        set(value) {
            cardsAdapter.onItemClickListener = value
            field = value
        }

    init {
        inflate(context, R.layout.player_single_battle_line_view, this)
        recyclerView.adapter = cardsAdapter
    }

    fun setData(cards: List<Card>) {
        cardsAdapter.setData(cards)
        totalPoints = cards.sumBy { card -> card.points }
    }
}