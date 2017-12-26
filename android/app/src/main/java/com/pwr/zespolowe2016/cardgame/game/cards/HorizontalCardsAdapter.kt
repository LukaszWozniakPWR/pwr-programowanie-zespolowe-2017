package com.pwr.zespolowe2016.cardgame.game.cards

import android.content.Context
import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseAdapter
import com.pwr.zespolowe2016.cardgame.other.recyclerview.ClickableBaseAdapter

class HorizontalCardsAdapter(private val context: Context) : ClickableBaseAdapter<HorizontalCardsViewHolder, Card>() {

    override fun layoutId(viewType: Int) = R.layout.cards_list_item

    override fun createHolder(view: View, viewType: Int) = HorizontalCardsViewHolder(context, view, onItemClickListener)
}
