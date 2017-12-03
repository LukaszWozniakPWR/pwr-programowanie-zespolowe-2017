package com.pwr.zespolowe2016.cardgame.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.extensions.displayChild
import com.pwr.zespolowe2016.cardgame.other.extensions.hideKeyboard
import com.pwr.zespolowe2016.cardgame.other.extensions.string
import com.pwr.zespolowe2016.cardgame.sockets.EmptyApiCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity

class MenuActivity : SocketApiActivity() {

    override val apiCallback = MenuActivityApiCallback(this)

    override val layoutId: Int = R.layout.activity_menu
    override val navigation = Navigation(this)

    private val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    private val startButton: Button by bindView(R.id.startButton)
    private val nicknameEditText: EditText by bindView(R.id.nicknameEditText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startButton.setOnClickListener { startButtonClicked() }
    }

    private fun startButtonClicked() {
        socketApi?.let { socketApi ->
            socketApi.setNickname(nicknameEditText.string)
            nicknameEditText.hideKeyboard()
            viewAnimator.displayChild(PROGRESS_INDEX)
        }
    }

    inner class MenuActivityApiCallback(private val context: Context) : EmptyApiCallback() {

        override fun onSetNicknameResponse(success: Boolean) {
            if (success) {
                navigation.startPlayerListActivity()
            } else {
                Toast.makeText(context, R.string.nickname_incorrect, Toast.LENGTH_LONG).show()
            }
            viewAnimator.displayChild(CONTENT_INDEX)
        }

        override fun onConnectionLost() {
            viewAnimator.displayChild(CONTENT_INDEX)
        }
    }

    companion object {
        private const val CONTENT_INDEX = 0
        private const val PROGRESS_INDEX = 1
        fun getIntent(context: Context) = Intent(context, MenuActivity::class.java)
    }
}