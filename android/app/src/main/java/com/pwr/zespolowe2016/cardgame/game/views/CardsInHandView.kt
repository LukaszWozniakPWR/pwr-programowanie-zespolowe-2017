package com.pwr.zespolowe2016.cardgame.game.views

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.CardsBottomSheetFragment
import com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards.MiniatureCardsAdapter
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class CardsInHandView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val recyclerView: RecyclerView by bindView(R.id.recyclerView)

    private val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val cardsAdapter: MiniatureCardsAdapter = MiniatureCardsAdapter()
    private val cardsBottomSheetFragment = CardsBottomSheetFragment()

    private val supportFragmentManager: FragmentManager
        get() = (context as FragmentActivity).supportFragmentManager

    var onCardClickListener: (Int, Card) -> Unit = cardsAdapter.onItemClickListener
        set(value) {
            cardsBottomSheetFragment.onCardClickListener = value
            field = value
        }

    var cardList: List<Card> = emptyList()
        set(value) {
            cardsAdapter.setData(value)
            field = value
        }

    init {
        inflate(context, R.layout.cards_in_hand_view, this)
        recyclerView.adapter = cardsAdapter
        recyclerView.layoutManager = layoutManager
        cardsAdapter.onItemClickListener = { i, card -> onCardClicked(card) }
    }

    private fun onCardClicked(card: Card) {
        cardsBottomSheetFragment.scrollToPosition(cardList.indexOf(card))
        cardsBottomSheetFragment.loadCards(context, cardList)
        cardsBottomSheetFragment.show(supportFragmentManager, cardsBottomSheetFragment.tag)
    }
}