package com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.Card
import com.pwr.zespolowe2016.cardgame.other.recyclerview.ClickableBaseAdapter

class MiniatureCardsAdapter : ClickableBaseAdapter<MiniatureCardsViewHolder, Card>() {

    override fun layoutId(viewType: Int) = R.layout.miniature_card_list_item

    override fun createHolder(view: View, viewType: Int) = MiniatureCardsViewHolder(view, onItemClickListener)
}