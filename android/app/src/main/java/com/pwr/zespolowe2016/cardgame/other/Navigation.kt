package com.pwr.zespolowe2016.cardgame.other


import android.app.Activity
import android.content.Intent
import com.pwr.zespolowe2016.cardgame.game.GameActivity
import com.pwr.zespolowe2016.cardgame.playerlist.PlayerListActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState

class Navigation(private val context: Activity) {

    fun startPlayerListActivity(requestCode: Int) {
        startActivityForResult(PlayerListActivity.getIntent(context), requestCode)
    }

    fun startGameActivity(initialGameState: GameState, requestCode: Int) {
        startActivityForResult(GameActivity.getIntent(context, initialGameState), requestCode)
    }

    private fun startActivity(intent: Intent) {
        context.startActivity(intent)
    }

    private fun startActivityForResult(intent: Intent, requestCode: Int) {
        context.startActivityForResult(intent, requestCode)
    }

    fun finishWithResultOk() {
        context.setResult(Activity.RESULT_OK)
        context.finish()
    }
}