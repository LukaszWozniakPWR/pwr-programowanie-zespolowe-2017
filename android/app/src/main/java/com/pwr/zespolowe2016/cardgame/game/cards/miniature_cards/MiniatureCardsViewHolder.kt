package com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolderWithIndex
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class MiniatureCardsViewHolder(
        view: View,
        private val onClickListener: (Int, Card) -> Unit) : BaseViewHolderWithIndex<Card>(view
) {

    private val miniatureCardView: MiniatureCardView by bindView(R.id.card_view)

    override fun displayItem(position: Int, itemType: Card) {
        miniatureCardView.setOnClickListener { onClickListener.invoke(position, itemType) }
        miniatureCardView.displayCard(itemType)
    }
}