package com.pwr.zespolowe2016.cardgame.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.playerlist.PlayerListActivity
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity

class GameActivity : SocketApiActivity() {

    override val layoutId: Int = R.layout.activity_game
    override val navigation = Navigation(this)

    private val playerView: TextView by bindView(R.id.player_view)

    private lateinit var playerNick: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerNick = intent.getStringExtra(PLAYER_NICK_KEY)
        playerView.text = "grasz z " + playerNick
    }

    companion object {
        private val PLAYER_NICK_KEY = "player_nick"
        fun getIntent(context: Context, playerNick: String) =
                Intent(context, PlayerListActivity::class.java)
                        .putExtra(PLAYER_NICK_KEY, playerNick)
    }
}