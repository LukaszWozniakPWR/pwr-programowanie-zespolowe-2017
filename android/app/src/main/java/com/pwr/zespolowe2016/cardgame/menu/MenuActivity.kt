package com.pwr.zespolowe2016.cardgame.menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.ViewAnimator
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.Card
import com.pwr.zespolowe2016.cardgame.game.CardType
import com.pwr.zespolowe2016.cardgame.game.CardsBottomSheetFragment
import com.pwr.zespolowe2016.cardgame.game.CardsDialog
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

    private val viewAnimator: ViewAnimator by bindView(R.id.viewAnimator)
    private val startButton: Button by bindView(R.id.startButton)
    //TODO remove fake button and bottomsheet
    private val fakeButton: Button by bindView(R.id.fakeButotn)
    private val cardsBottomSheetFragment = CardsBottomSheetFragment()
    private val fakeButton2: Button by bindView(R.id.fakeButotn2)
    // TODO

    private val nicknameEditText: EditText by bindView(R.id.nicknameEditText)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startButton.setOnClickListener { startButtonClicked() }

        val cards = mutableListOf<Card>()
        cards.add(Card(10, "Karta 1", -1, CardType.ONE, "super opis karty 1"))
        cards.add(Card(200, "Karta 2", -1, CardType.TWO, "super opis karty 2"))
        cards.add(Card(300, "Karta 3", -1, CardType.ONE, "super opis karty 3"))
        cards.add(Card(400, "Karta 4", -1, CardType.TWO, "super opis karty 4"))
        fakeButton.setOnClickListener {
            CardsDialog(this).apply { loadCards(cards) }.show()
        }
        fakeButton2.setOnClickListener {
            cardsBottomSheetFragment.show(supportFragmentManager, cardsBottomSheetFragment.tag)
            cardsBottomSheetFragment.loadCards(this, cards)
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
                navigation.startGameActivity(nickname)
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
        fun getIntent(context: Context) = Intent(context, MenuActivity::class.java)
    }
}