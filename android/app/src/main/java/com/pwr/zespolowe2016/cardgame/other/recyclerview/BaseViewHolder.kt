package com.pwr.zespolowe2016.cardgame.other.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder<ItemType>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun displayItem(itemType: ItemType)
}