package com.pwr.zespolowe2016.cardgame.playerlist

import android.view.View
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.recyclerview.ClickableBaseAdapter
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

class PlayerListAdapter : ClickableBaseAdapter<PlayerListViewHolder, Player>() {

    override fun layoutId(viewType: Int) = R.layout.player_list_item

    override fun createHolder(view: View, viewType: Int)
            = PlayerListViewHolder(view, onItemClickListener)
}