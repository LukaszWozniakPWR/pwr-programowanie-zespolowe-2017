package com.pwr.zespolowe2016.cardgame.game.cards

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseAdapter
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Attribute

class AttributesAdapter : BaseAdapter<AttributeViewHolder, Attribute>() {

    override fun layoutId(viewType: Int): Int = R.layout.attribute_list_item

    override fun createHolder(view: View, viewType: Int) = AttributeViewHolder(view)
}