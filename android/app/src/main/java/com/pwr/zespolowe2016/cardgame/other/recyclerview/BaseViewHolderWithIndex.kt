package com.pwr.zespolowe2016.cardgame.other.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseViewHolderWithIndex<ItemType>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun displayItem(position: Int, itemType: ItemType)
}