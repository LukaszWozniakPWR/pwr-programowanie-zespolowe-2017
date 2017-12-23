package com.pwr.zespolowe2016.cardgame.game

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder

class CardsViewHolder(view: View) : BaseViewHolder<Card>(view) {

    private val cardView: CardView by bindView(R.id.card_view)

    override fun displayItem(itemType: Card) {
        cardView.displayCard(itemType)
    }
}