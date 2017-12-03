package com.pwr.zespolowe2016.cardgame.other.recyclerview

abstract class ClickableBaseAdapter<HolderType : BaseViewHolder<ItemType>, ItemType : Any>
    : BaseAdapter<HolderType, ItemType>() {

    var onItemClickListener: (ItemType) -> Unit = { /* NO-OP */ }
}