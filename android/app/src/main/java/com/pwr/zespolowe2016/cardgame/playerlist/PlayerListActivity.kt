package com.pwr.zespolowe2016.cardgame.playerlist

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
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
    val pullToRefresh: SwipeRefreshLayout by bindView(R.id.pullToRefresh)

    val invitePlayerMessageText: String by bindString(R.string.invite_player_message)
    val playerRefusedMessageText: String by bindString(R.string.player_refused_message)
    val playerRequestedGameMessageText: String by bindString(R.string.player_requested_game_message)

    val playerListAdapter = PlayerListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerListAdapter.onItemClickListener = { player -> invitePlayer(player) }
        recyclerView.adapter = playerListAdapter
        pullToRefresh.setColorSchemeResources(R.color.greenGoblin)
        pullToRefresh.setOnRefreshListener { socketApi?.getPlayerList() }
    }

    private fun invitePlayer(player: Player) {
        AlertDialog.Builder(this)
                .setTitle(R.string.invite_player_title)
                .setMessage(invitePlayerMessageText.format(player.name))
                .setNegativeButton(R.string.dialog_no) { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(R.string.dialog_yes) { dialog, _ ->
                    socketApi?.requestGameWithPlayer(player.name)
                    dialog.dismiss()
                }.create()
                .show()
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

        override fun onGameRequested(nickname: String) {
            AlertDialog.Builder(this@PlayerListActivity)
                    .setTitle(R.string.player_requested_game_title)
                    .setMessage(playerRequestedGameMessageText.format(nickname))
                    .setNegativeButton(R.string.dialog_no) { dialog, _ ->
                        socketApi?.refuseGameRequestFrom(nickname)
                        dialog.dismiss()
                    }
                    .setPositiveButton(R.string.dialog_yes) { dialog, _ ->
                        socketApi?.requestGameWithPlayer(nickname)
                        dialog.dismiss()
                    }.create()
                    .show()
        }

        override fun onRequestGameResponse(playerAccepted: Boolean, nickname: String) {
            if (playerAccepted) {
                navigation.startGameActivity(nickname)
            } else {
                AlertDialog.Builder(this@PlayerListActivity)
                        .setTitle(R.string.player_refused_title)
                        .setMessage(playerRefusedMessageText.format(nickname))
                        .setNeutralButton(R.string.dialog_ok) { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
            }
        }

        override fun onConnectionLost() {
            finish()
        }
    }

    companion object {
        private const val PROGRESS_INDEX = 0
        private const val CONTENT_INDEX = 1
        private const val EMPTY_LIST_INDEX = 2
        fun getIntent(context: Context) = Intent(context, PlayerListActivity::class.java)
    }
}