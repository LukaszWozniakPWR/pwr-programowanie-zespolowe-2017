package com.pwr.zespolowe2016.cardgame.other


import android.app.Activity
import android.content.Intent
import com.pwr.zespolowe2016.cardgame.game.GameActivity
import com.pwr.zespolowe2016.cardgame.playerlist.PlayerListActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState

class Navigation(private val context: Activity) {

    fun startPlayerListActivity() {
        startActivity(PlayerListActivity.getIntent(context))
    }

    fun startGameActivity(initialGameState: GameState) {
        startActivity(GameActivity.getIntent(context, initialGameState))
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent)
    }

    private fun startActivityForResult(intent: Intent, requestCode: Int) {
        context.startActivityForResult(intent, requestCode)
    }
}