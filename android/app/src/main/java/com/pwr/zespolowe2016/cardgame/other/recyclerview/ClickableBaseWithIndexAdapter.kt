package com.pwr.zespolowe2016.cardgame.other.recyclerview

abstract class ClickableBaseWithIndexAdapter<HolderType : BaseViewHolderWithIndex<ItemType>, ItemType : Any>
    : BaseAdapterWithIndex<HolderType, ItemType>() {

    var onItemClickListener: (Int, ItemType) -> Unit = { i, itemType -> /* NO-OP */ }
}
