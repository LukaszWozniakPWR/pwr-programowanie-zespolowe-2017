package com.pwr.zespolowe2016.cardgame.game

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.views.CardsInHandView
import com.pwr.zespolowe2016.cardgame.game.views.PlayerView
import com.pwr.zespolowe2016.cardgame.game.views.battle_view.PlayerBattleFieldView
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.other.extensions.visible
import com.pwr.zespolowe2016.cardgame.sockets.EmptyApiCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.*

class GameActivity : SocketApiActivity() {

    override val apiCallback = GameActivityApiCallback(this)

    override val layoutId: Int = R.layout.activity_game
    override val navigation = Navigation(this)

    private val pickRowContainer: LinearLayout by bindView(R.id.pickRowContainer)
    private val pickRowText: TextView by bindView(R.id.pickRowText)
    private val cancelPickingRowButton: Button by bindView(R.id.cancelPickingRowButton)
    private val yourPlayerView: PlayerView by bindView(R.id.yourPlayerView)
    private val otherPlayerView: PlayerView by bindView(R.id.otherPlayerView)
    private val yourBattleFieldView: PlayerBattleFieldView by bindView(R.id.yourBattleField)
    private val otherPlayerBattleFieldView: PlayerBattleFieldView by bindView(R.id.otherPlayerBattleField)
    private val cardsInHandView: CardsInHandView by bindView(R.id.cardsInHandView)

    private var gameState: GameState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameState = intent.getParcelableExtra(INITIAL_GAME_STATE_KEY)
        cancelPickingRowButton.setOnClickListener { cancelPickingRow() }
        yourPlayerView.addResignButtonListener { socketApi?.pass() }
        cardsInHandView.onCardClickListener = { i, card -> onCardInHandClicked(i, card) }
        refreshInfo()
    }

    override fun onBackPressed() { /* NO-OP */ }

    private fun onCardInHandClicked(cardIndex: Int, card: Card) {
        when (card.cardClass.rowInfo) {
            RowInfo.ANY_OF_OPPONENTS -> pickOpponentRowAndPutCard(cardIndex, card)
            RowInfo.ANY_OF_YOURS -> pickYourRowAndPutCard(cardIndex, card)
            else -> sendPutCardRequest(cardIndex, card.cardClass.rowInfo.rowNumber)
        }
    }

    private fun pickOpponentRowAndPutCard(cardIndex: Int, card: Card) {
        pickRowContainer.visible = true
        pickRowText.setText(R.string.pick_opponent_row)
        otherPlayerBattleFieldView.pickRowWithNextClick { rowNumber ->
            sendPutCardRequest(cardIndex, rowNumber)
            cancelPickingRow()
        }
    }

    private fun pickYourRowAndPutCard(cardIndex: Int, card: Card) {
        pickRowContainer.visible = true
        pickRowText.setText(R.string.pick_your_row)
        yourBattleFieldView.pickRowWithNextClick { rowNumber ->
            sendPutCardRequest(cardIndex, rowNumber)
            cancelPickingRow()
        }
    }

    private fun sendPutCardRequest(cardIndex: Int, rowNumber: Int) {
        socketApi?.putCard(cardIndex, rowNumber)
    }

    private fun cancelPickingRow() {
        pickRowContainer.visible = false
    }

    private fun refreshInfo() {
        gameState ?.let {
            loadTurn(it.turn)
            loadNames(it.you.name, it.opponent.name)
            loadInfo(it.yourState, it.opponentState)
            loadCards(it.yourState, it.opponentState)
        }
    }

    private fun loadTurn(turn: Turn) {
        if (turn == Turn.YOUR) {
            yourPlayerView.showTurnAndResignButton()
            otherPlayerView.hideTurn()
            cardsInHandView.onCardClickListener = { i, card ->
                socketApi?.putCard(i, card.cardClass.rowInfo.rowNumber)
            }
        } else {
            otherPlayerView.showTurnAndHideResignButton()
            yourPlayerView.hideTurn()
            cardsInHandView.onCardClickListener = { i, card -> }
        }
    }

    private fun loadNames(you: String, opponent: String) {
        yourPlayerView.playerName = you
        otherPlayerView.playerName = opponent
    }

    private fun loadInfo(yourState: YourState, opponentState: OpponentState) {
        loadInfo(yourState)
        loadInfo(opponentState)
    }

    private fun loadInfo(yourState: YourState) {
        yourPlayerView.cardsCount = yourState.numberOfCardsInHand
        yourPlayerView.pointsCount = yourState.points
        yourPlayerView.roundsWon = yourState.roundsWon
    }

    private fun loadInfo(opponentState: OpponentState) {
        otherPlayerView.cardsCount = opponentState.numberOfCardsInHand
        otherPlayerView.pointsCount = opponentState.points
        otherPlayerView.roundsWon = opponentState.roundsWon
    }

    private fun loadCards(yourState: YourState, opponentState: OpponentState) {
        loadCards(yourState.cardsInHand, yourState.swordsRow, yourState.archersRow, yourState.catapultsRow)
        loadCards(opponentState.swordsRow, opponentState.archersRow, opponentState.catapultsRow)
    }

    private fun loadCards(cardsInHand: List<Card>, swordsRow: Row, archersRow: Row, catapultsRow: Row) {
        cardsInHandView.cardList = cardsInHand
        yourBattleFieldView.setSwordsData(swordsRow.battleCards)
        yourBattleFieldView.setArchersData(archersRow.battleCards)
        yourBattleFieldView.setCatapultsData(catapultsRow.battleCards)
    }

    private fun loadCards(swordsRow: Row, archersRow: Row, catapultsRow: Row) {
        otherPlayerBattleFieldView.setSwordsData(swordsRow.battleCards)
        otherPlayerBattleFieldView.setArchersData(archersRow.battleCards)
        otherPlayerBattleFieldView.setCatapultsData(catapultsRow.battleCards)
    }

    inner class GameActivityApiCallback(private val context: Context) : EmptyApiCallback() {
        //TODO onConnectionLost

        override fun youWonResponse() {
            AlertDialog.Builder(this@GameActivity)
                    .setTitle("Gratulacje")
                    .setMessage("Wygrałeś rozgrywkę.")
                    .setPositiveButton("OK") { dialog, which ->
                        this@GameActivity.finish()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                    .apply {
                        setCanceledOnTouchOutside(false)
                        show()
                    }
        }

        override fun youLostResponse() {
            AlertDialog.Builder(this@GameActivity)
                    .setTitle("Przegrałeś")
                    .setMessage("Może następnym razem się uda, powodzenia!")
                    .setPositiveButton("OK") { dialog, which ->
                        this@GameActivity.finish()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                    .apply {
                        setCanceledOnTouchOutside(false)
                        show()
                    }
        }

        override fun opponentDisconnected() {
            AlertDialog.Builder(this@GameActivity)
                    .setTitle("Przeciwnik się rozłączył")
                    .setMessage("Niestety przeciwnik utracił połączenie, wróć do listy graczy.")
                    .setPositiveButton("OK") { dialog, which ->
                        this@GameActivity.finish()
                        dialog.dismiss()
                    }
                    .setCancelable(false)
                    .create()
                    .apply {
                        setCanceledOnTouchOutside(false)
                        show()
                    }
        }

        override fun putCardResponse(success: Boolean, gameStateAfterYourMove: GameState) {
            gameState = gameStateAfterYourMove
            if (success) {
                refreshInfo()
            } else {
                AlertDialog.Builder(this@GameActivity)
                        .setTitle("Błąd")
                        .setMessage("Niestety karta sie nie dodała, błąd systemu!")
                        .setPositiveButton("OK", { dialog, which ->
                            refreshInfo()
                            dialog.dismiss()
                        }
                        )
                        .setCancelable(false)
                        .create()
                        .apply {
                            setCanceledOnTouchOutside(false)
                            show()
                        }
            }
        }

        override fun opponentActionResponse(gameStateAfterOpponentMove: GameState) {
            gameState = gameStateAfterOpponentMove
            refreshInfo()
        }

        override fun passResponse(success: Boolean, gameStateAfterYourPass: GameState) {
            if (success) {
                gameState = gameStateAfterYourPass
                refreshInfo()
            }
        }
    }

    companion object {
        private val INITIAL_GAME_STATE_KEY = "initial_game_state"
        fun getIntent(context: Context, initialGameState: GameState) =
                Intent(context, GameActivity::class.java)
                        .putExtra(INITIAL_GAME_STATE_KEY, initialGameState)
    }
}