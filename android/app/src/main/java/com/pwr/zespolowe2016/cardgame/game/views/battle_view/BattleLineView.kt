package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.Card
import com.pwr.zespolowe2016.cardgame.game.cards.CardsDialog
import com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards.MiniatureCardsAdapter
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

    private val cardsAdapter: MiniatureCardsAdapter = MiniatureCardsAdapter()
    private val cardsDialog: CardsDialog = CardsDialog(context)

    var onCardClickListener: (Card) -> Unit = cardsAdapter.onItemClickListener
        set(value) {
            cardsDialog.onCardClickListener = value
            field = value
        }

    var cardList: List<Card> = emptyList()
        set(value) {
            cardsAdapter.setData(value)
            totalPoints = value.sumBy { card -> card.points }
            field = value
        }

    init {
        inflate(context, R.layout.player_single_battle_line_view, this)
        recyclerView.adapter = cardsAdapter
        cardsAdapter.onItemClickListener = { card -> onCardClicked(card) }
    }

    private fun onCardClicked(card: Card) {
        cardsDialog.loadCards(cardList)
        cardsDialog.scrollToPosition(cardList.indexOf(card))
        cardsDialog.show()
    }
}