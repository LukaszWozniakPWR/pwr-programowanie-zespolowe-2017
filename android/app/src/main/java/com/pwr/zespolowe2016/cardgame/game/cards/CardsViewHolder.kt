package com.pwr.zespolowe2016.cardgame.game.cards

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class CardsViewHolder(view: View, private val onClickListener: (Card) -> Unit) : BaseViewHolder<Card>(view) {

    private val cardView: CardView by bindView(R.id.card_view)

    override fun displayItem(itemType: Card) {
        cardView.setOnClickListener { onClickListener.invoke(itemType) }
        cardView.displayCard(itemType)
    }
}