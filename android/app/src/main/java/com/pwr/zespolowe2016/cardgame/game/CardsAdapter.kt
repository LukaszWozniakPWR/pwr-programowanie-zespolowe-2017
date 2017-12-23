package com.pwr.zespolowe2016.cardgame.game

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseAdapter

class CardsAdapter : BaseAdapter<CardsViewHolder, Card>() {

    override fun layoutId(viewType: Int) = R.layout.cards_list_item

    override fun createHolder(view: View, viewType: Int)
            = CardsViewHolder(view)
}