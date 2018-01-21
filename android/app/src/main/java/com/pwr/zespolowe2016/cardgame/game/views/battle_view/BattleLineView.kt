package com.pwr.zespolowe2016.cardgame.game.views.battle_view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.CardsDialog
import com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards.MiniatureCardsAdapter
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.extensions.visible
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Effect
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Effect.BAD_WEATHER
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Effect.COMMANDERS_HORN

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
    private val selectButton: Button by bindView(R.id.selectButton)

    private val cardsAdapter: MiniatureCardsAdapter = MiniatureCardsAdapter()
    private val cardsDialog: CardsDialog = CardsDialog(context)

    var onCardClickListener: (Int, Card) -> Unit = cardsAdapter.onItemClickListener
        set(value) {
            cardsDialog.onCardClickListener = value
            field = value
        }

    var cardList: List<Card> = emptyList()
        set(value) {
            cardsAdapter.setData(value)
            totalPoints = value.sumBy { card -> card.actualStrength }
            field = value
        }

    var effectsList: List<Effect> = emptyList()
        set(value) {
            recyclerView.alpha = 0.5f
            if (value.contains(BAD_WEATHER) && value.contains(COMMANDERS_HORN)) {
                recyclerView.setBackgroundResource(R.color.orangeCarrot)
            } else if (value.contains(BAD_WEATHER)) {
                recyclerView.setBackgroundResource(R.color.greenGoblin)
            } else if (value.contains(COMMANDERS_HORN)) {
                recyclerView.setBackgroundResource(R.color.brownRed)
            } else {
                recyclerView.alpha = 1.0f
            }
        }

    init {
        inflate(context, R.layout.player_single_battle_line_view, this)
        recyclerView.adapter = cardsAdapter
        cardsAdapter.onItemClickListener = { position, card -> onCardClicked(card) }
    }

    fun setSelectable(listener: () -> Unit) {
        selectButton.setOnClickListener { listener() }
        selectButton.visible = true
    }

    fun setNotSelectable() {
        selectButton.visible = false
    }

    private fun onCardClicked(card: Card) {
        cardsDialog.loadCards(cardList)
        cardsDialog.scrollToPosition(cardList.indexOf(card))
        cardsDialog.show()
    }
}