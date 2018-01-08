package com.pwr.zespolowe2016.cardgame.other.recyclerview

import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseAdapter<HolderType : BaseViewHolder<ItemType>, ItemType : Any>
    : RecyclerView.Adapter<HolderType>() {

    private val items: MutableList<ItemType> = mutableListOf()

    @LayoutRes abstract fun layoutId(viewType: Int): Int

    abstract fun createHolder(view: View, viewType: Int): HolderType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderType {
        return createHolder(
                LayoutInflater.from(parent.context).inflate(
                        layoutId(viewType),
                        parent,
                        false
                ), viewType
        )
    }

    override fun onBindViewHolder(holder: HolderType, position: Int) {
        holder.displayItem(items[position])
    }

    override fun getItemCount() = items.size

    open fun setData(data: List<ItemType>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }
}