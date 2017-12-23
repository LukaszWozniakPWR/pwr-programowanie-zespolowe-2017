package com.pwr.zespolowe2016.cardgame.other

import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import com.pwr.zespolowe2016.cardgame.R

class DialogCreator(private val context: Context) {

    private val invitePlayerMessageText: String by bindString(R.string.invite_player_message)
    private val playerRefusedMessageText: String by bindString(R.string.player_refused_message)
    private val playerRequestedGameMessageText: String by bindString(R.string.player_requested_game_message)

    private val stringFinder: DialogCreator.(Int) -> String?
        get() = { context.resources?.getString(it) }

    private fun DialogCreator.bindString(id: Int): Lazy<DialogCreator, String> {
        return requiredString(id, stringFinder)
    }

    fun showInvitePlayerDialog(
            nickname: String,
            positiveButtonCallback: (DialogInterface, Int) -> Unit,
            negativeButtonCallback: (DialogInterface, Int) -> Unit = { _,_ -> /* NO-OP */ }
    ) {
        AlertDialog.Builder(context)
                .setTitle(R.string.invite_player_title)
                .setMessage(invitePlayerMessageText.format(nickname))
                .setNegativeButton(R.string.dialog_no) { dialog, which ->
                    negativeButtonCallback(dialog, which)
                    dialog.dismiss()
                }
                .setPositiveButton(R.string.dialog_yes) { dialog, which ->
                    positiveButtonCallback(dialog, which)
                    dialog.dismiss()
                }.create()
                .show()
    }

    fun showGameRequestedDialog(
            nickname: String,
            positiveButtonCallback: (DialogInterface, Int) -> Unit,
            negativeButtonCallback: (DialogInterface, Int) -> Unit
    ) {
        AlertDialog.Builder(context)
                .setTitle(R.string.player_requested_game_title)
                .setMessage(playerRequestedGameMessageText.format(nickname))
                .setCancelable(false)
                .setPositiveButton(R.string.dialog_yes) { dialog, which ->
                    positiveButtonCallback(dialog, which)
                    dialog.dismiss()
                }
                .setNegativeButton(R.string.dialog_no) { dialog, which ->
                    negativeButtonCallback(dialog, which)
                    dialog.dismiss()
                }.create()
                .show()
    }

    fun showGameRefusedDialog(nickname: String) {
        AlertDialog.Builder(context)
                .setTitle(R.string.player_refused_title)
                .setMessage(playerRefusedMessageText.format(nickname))
                .setNeutralButton(R.string.dialog_ok) { dialog, _ -> dialog.dismiss() }
                .create()
                .show()
    }

    fun showConnectionLostDialog(onDismissCallback: (DialogInterface) -> Unit = { /* NO-OP */ }) {
        AlertDialog.Builder(context)
                .setTitle(R.string.connection_lost_title)
                .setMessage(R.string.connection_lost_message)
                .setCancelable(false)
                .setNeutralButton(R.string.dialog_ok) { dialog, _ -> dialog.dismiss() }
                .setOnDismissListener { dialog -> onDismissCallback(dialog) }
                .create()
                .show()
    }
}