package com.pwr.zespolowe2016.cardgame.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.views.CardsInHandView
import com.pwr.zespolowe2016.cardgame.game.views.PlayerView
import com.pwr.zespolowe2016.cardgame.game.views.battle_view.PlayerBattleFieldView
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.sockets.EmptyApiCallback
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.*

class GameActivity : SocketApiActivity() {

    override val apiCallback = GameActivityApiCallback(this)

    override val layoutId: Int = R.layout.activity_game
    override val navigation = Navigation(this)

    private val yourPlayerView: PlayerView by bindView(R.id.yourPlayerView)
    private val otherPlayerView: PlayerView by bindView(R.id.otherPlayerView)
    private val yourBattleFieldView: PlayerBattleFieldView by bindView(R.id.yourBattleField)
    private val otherPlayerBattleFieldView: PlayerBattleFieldView by bindView(R.id.otherPlayerBattleField)
    private val cardsInHandView: CardsInHandView by bindView(R.id.cardsInHandView)

    private var gameState: GameState? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameState = intent.getParcelableExtra(INITIAL_GAME_STATE_KEY)
        yourPlayerView.addResignButtonListener { socketApi?.pass() }
        refreshInfo()

//        val cards = mutableListOf<Card>()
//        cards.add(Card(10, "Karta 1", -1, ONE, "super opis karty 1"))
//        cards.add(Card(20, "Karta 2", -1, TWO, "super opis karty 2"))
//        cards.add(Card(30, "Karta 3", -1, ONE, "super opis karty 3"))
//        cards.add(Card(40, "Karta 4", -1, TWO, "super opis karty 4"))
//        yourBattleFieldView.setCatapultsData(cards.subList(0, 2))
//        yourBattleFieldView.setArchersData(cards.subList(0, 1))
//        otherPlayerBattleFieldView.setSwordsData(cards)
//        cardsInHandView.cardList = cards
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
        } else {
            otherPlayerView.showTurnAndHideResignButton()
            yourPlayerView.hideTurn()
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
        //TODO lifes
    }

    private fun loadInfo(opponentState: OpponentState) {
        otherPlayerView.cardsCount = opponentState.numberOfCardsInHand
        otherPlayerView.pointsCount = opponentState.points
        //TODO lifes
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

    }

    companion object {
        private val INITIAL_GAME_STATE_KEY = "initial_game_state"
        fun getIntent(context: Context, initialGameState: GameState) =
                Intent(context, GameActivity::class.java)
                        .putExtra(INITIAL_GAME_STATE_KEY, initialGameState)
    }
}