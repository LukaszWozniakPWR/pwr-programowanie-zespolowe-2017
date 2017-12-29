package com.pwr.zespolowe2016.cardgame.menu

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.widget.*
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.menu.animation.MenuAnimation
import com.pwr.zespolowe2016.cardgame.menu.animation.OnEndAnimatorListener
import com.pwr.zespolowe2016.cardgame.other.DialogCreator
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

    private val dialogCreator = DialogCreator(this)

    private val mainContainer: LinearLayout by bindView(R.id.mainContainer)
    private val appLogoImageView: ImageView by bindView(R.id.appLogo)
    private val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    private val startButton: Button by bindView(R.id.startButton)

    private val fakeButton3: Button by bindView(R.id.fakeButotn3)
    private val nicknameEditText: EditText by bindView(R.id.nicknameEditText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startButton.setOnClickListener { startButtonClicked() }
        //fakeButton3.setOnClickListener { navigation.startGameActivity("fakeEnemyNickname") }
        mainContainer.post {
            MenuAnimation.Builder()
                    .build(mainContainer, appLogoImageView, viewAnimator, object : OnEndAnimatorListener() {
                        override fun onAnimationEnd(animation: Animator) {

                        }
                    })
                    .startAnimations()
        }
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

        override fun onGameRequested(nickname: String) {
            dialogCreator.showGameRequestedDialog(
                    nickname,
                    positiveButtonCallback = { _, _ -> socketApi?.requestGameWithPlayer(nickname) },
                    negativeButtonCallback = { _, _ -> socketApi?.refuseGameRequestFrom(nickname) }
            )
        }

        override fun onRequestGameResponse(playerAccepted: Boolean, nickname: String) {
            if (playerAccepted) {
                //navigation.startGameActivity(nickname)
            } else {
                dialogCreator.showGameRefusedDialog(nickname)
            }
        }

        override fun onConnectionLost() {
            viewAnimator.displayChild(CONTENT_INDEX)
        }
    }

    companion object {
        private const val CONTENT_INDEX = 0
        private const val PROGRESS_INDEX = 1
    }
}