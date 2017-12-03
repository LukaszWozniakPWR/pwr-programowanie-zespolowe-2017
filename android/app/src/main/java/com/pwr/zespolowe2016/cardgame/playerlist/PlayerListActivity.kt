package com.pwr.zespolowe2016.cardgame.playerlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.extensions.displayChild
import com.pwr.zespolowe2016.cardgame.sockets.EmptyApiCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

class PlayerListActivity : SocketApiActivity() {

    override val layoutId = R.layout.activity_player_list
    override val navigation =  Navigation(this)

    val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    val recyclerView: RecyclerView by bindView(R.id.recyclerView)

    val playerListAdapter = PlayerListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recyclerView.adapter = playerListAdapter
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        socketApi?.getPlayerList()
    }

    override val apiCallback = object : EmptyApiCallback() {
        override fun onPlayerList(playerList: List<Player>) {
            playerListAdapter.setData(playerList)
            viewAnimator.displayChild(if (playerList.isEmpty()) EMPTY_LIST_INDEX else CONTENT_INDEX)
        }
    }

    companion object {
        private const val PROGRESS_INDEX = 0
        private const val CONTENT_INDEX = 1
        private const val EMPTY_LIST_INDEX = 2
        fun getIntent(context: Context) = Intent(context, PlayerListActivity::class.java)
    }
}