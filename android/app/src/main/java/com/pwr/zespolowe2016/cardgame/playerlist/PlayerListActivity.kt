package com.pwr.zespolowe2016.cardgame.playerlist

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.R.id.viewAnimator
import com.pwr.zespolowe2016.cardgame.other.DialogCreator
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.extensions.displayChild
import com.pwr.zespolowe2016.cardgame.other.extensions.setRefreshingSafely
import com.pwr.zespolowe2016.cardgame.sockets.EmptyApiCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

class PlayerListActivity : SocketApiActivity() {

    override val layoutId = R.layout.activity_player_list
    override val navigation =  Navigation(this)

    val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    val recyclerView: RecyclerView by bindView(R.id.recyclerView)
    val pullToRefresh: SwipeRefreshLayout by bindView(R.id.pullToRefresh)

    private val dialogCreator = DialogCreator(this)
    private val playerListAdapter = PlayerListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerListAdapter.onItemClickListener = { player -> invitePlayer(player) }
        recyclerView.adapter = playerListAdapter
        pullToRefresh.setColorSchemeResources(R.color.greenGoblin)
        pullToRefresh.setOnRefreshListener { socketApi?.getPlayerList() }
    }

    private fun invitePlayer(player: Player) {
        dialogCreator.showInvitePlayerDialog(
                player.name,
                positiveButtonCallback = { _,_ -> socketApi?.requestGameWithPlayer(player.name) }
        )
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        socketApi?.getPlayerList()
    }

    override fun onBackPressed() { /* NO-OP */ }

    override val apiCallback = object : EmptyApiCallback() {

        private var isShowingConnectionLost = false

        override fun onPlayerList(playerList: List<Player>) {
            playerListAdapter.setData(playerList)
            viewAnimator.displayChild(if (playerList.isEmpty()) EMPTY_LIST_INDEX else CONTENT_INDEX)
            pullToRefresh.setRefreshingSafely(false)
        }

        override fun onGameRequested(nickname: String) {
            dialogCreator.showGameRequestedDialog(
                    nickname,
                    positiveButtonCallback = { _, _ -> socketApi?.requestGameWithPlayer(nickname) },
                    negativeButtonCallback = { _, _ -> socketApi?.refuseGameRequestFrom(nickname) }
            )
        }

        override fun onRequestGameResponse(playerAccepted: Boolean, nickname: String) {
            if (playerAccepted) {
                dialogCreator.showGameAcceptedDialogDialog(
                        nickname,
                        {
                            viewAnimator.displayedChild = PROGRESS_INDEX

                        })
            } else {
                dialogCreator.showGameRefusedDialog(nickname)
            }
        }

        override fun onConnectionLost() {
            if (isShowingConnectionLost) return else isShowingConnectionLost = true
            dialogCreator.showConnectionLostDialog { finish() }
        }

        override fun gameStartedResponse(initialGameState: GameState) {
            navigation.startGameActivity(initialGameState, GAME_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == GAME_ACTIVITY_REQUEST_CODE) {
            navigation.finishWithResultOk()
        }
    }

    companion object {
        private const val GAME_ACTIVITY_REQUEST_CODE = 2133
        private const val PROGRESS_INDEX = 0
        private const val CONTENT_INDEX = 1
        private const val EMPTY_LIST_INDEX = 2
        fun getIntent(context: Context) = Intent(context, PlayerListActivity::class.java)
    }
}