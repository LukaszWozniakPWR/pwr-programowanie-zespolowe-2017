package com.pwr.zespolowe2016.cardgame.menu

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.extensions.string
import com.pwr.zespolowe2016.cardgame.sockets.SocketAidlCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity

class MenuActivity : SocketApiActivity() {

    override val apiCallback = MenuActivityApiCallback()

    override val layoutId: Int = R.layout.activity_menu
    override val navigation = Navigation(this)

    private val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    private val startButton: Button by bindView(R.id.startButton)
    private val nicknameEditText: EditText by bindView(R.id.nicknameEditText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        startButton.setOnClickListener { startButtonClicked() }
    }

    private fun startButtonClicked() {
        socketApi?.let { socketApi ->
            socketApi.setNickname(nicknameEditText.string)
            viewAnimator.displayedChild = PROGRESS_INDEX
        }
    }

    inner class MenuActivityApiCallback : SocketAidlCallback.Stub() {

        override fun onSetNicknameResponse(success: Boolean) {
            viewAnimator.displayedChild = CONTENT_INDEX
            Toast.makeText(this@MenuActivity, "success $success", Toast.LENGTH_LONG)
                    .show()
        }
    }

    companion object {
        private const val CONTENT_INDEX = 0
        private const val PROGRESS_INDEX = 1
    }
}