package com.pwr.zespolowe2016.cardgame.playerlist

import android.view.View
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindString
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerStatus.FREE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.PlayerStatus.PLAYING

class PlayerListViewHolder(view: View) : BaseViewHolder<Player>(view) {

    private val playerNickname : TextView by bindView(R.id.playerNickname)
    private val playerStatus : TextView by bindView(R.id.playerStatus)
    private val freeStatusText: String by bindString(R.string.player_status_free)
    private val playingStatusText: String by bindString(R.string.player_status_playing)

    override fun displayItem(itemType: Player) {
        playerNickname.text = itemType.name
        playerStatus.text = when (itemType.status) {
            FREE -> freeStatusText
            PLAYING -> playingStatusText
        }
    }
}