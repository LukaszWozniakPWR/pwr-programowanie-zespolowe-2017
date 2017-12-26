package com.pwr.zespolowe2016.cardgame.game.cards

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView

class CardsDialog(context: Context) : AlertDialog(context) {

    private val cardsList: RecyclerView by bindView(R.id.cards_dialog_list)
    private val closeButton: Button by bindView(R.id.cards_dialog_close_button)

    private val cardsAdapter = CardsAdapter()
    private var positionToScroll = 0

    var onCardClickListener: (Card) -> Unit = cardsAdapter.onItemClickListener
        set(value) {
            cardsAdapter.onItemClickListener = value
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_dialog)
        closeButton.setOnClickListener { dismiss() }
        cardsList.adapter = cardsAdapter
        if (positionToScroll < cardsAdapter.itemCount) {
            cardsList.scrollToPosition(positionToScroll)
        }
    }

    fun scrollToPosition(position: Int) {
        positionToScroll = position
    }

    fun loadCards(cards: List<Card>) {
        cardsAdapter.setData(cards)
    }
}