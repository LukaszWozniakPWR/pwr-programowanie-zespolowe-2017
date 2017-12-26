package com.pwr.zespolowe2016.cardgame.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.Card
import com.pwr.zespolowe2016.cardgame.game.cards.CardType.ONE
import com.pwr.zespolowe2016.cardgame.game.cards.CardType.TWO
import com.pwr.zespolowe2016.cardgame.game.views.CardsInHandView
import com.pwr.zespolowe2016.cardgame.game.views.battle_view.PlayerBattleFieldView
import com.pwr.zespolowe2016.cardgame.game.views.PlayerView
import com.pwr.zespolowe2016.cardgame.other.Navigation
import com.pwr.zespolowe2016.cardgame.sockets.SocketApiActivity

class GameActivity : SocketApiActivity() {

    override val layoutId: Int = R.layout.activity_game
    override val navigation = Navigation(this)

    private val yourPlayerView: PlayerView by bindView(R.id.yourPlayerView)
    private val otherPlayerView: PlayerView by bindView(R.id.otherPlayerView)
    private val yourBattleFieldView: PlayerBattleFieldView by bindView(R.id.yourBattleField)
    private val otherPlayerBattleFieldView: PlayerBattleFieldView by bindView(R.id.otherPlayerBattleField)
    private val cardsInHandView: CardsInHandView by bindView(R.id.cardsInHandView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        yourPlayerView.playerName = "Twoja nazwa"
        otherPlayerView.playerName = intent.getStringExtra(PLAYER_NICK_KEY)

        val cards = mutableListOf<Card>()
        cards.add(Card(10, "Karta 1", -1, ONE, "super opis karty 1"))
        cards.add(Card(20, "Karta 2", -1, TWO, "super opis karty 2"))
        cards.add(Card(30, "Karta 3", -1, ONE, "super opis karty 3"))
        cards.add(Card(40, "Karta 4", -1, TWO, "super opis karty 4"))
        yourBattleFieldView.setCatapultsData(cards.subList(0, 2))
        yourBattleFieldView.setArchersData(cards.subList(0, 1))
        otherPlayerBattleFieldView.setSwordsData(cards)
        cardsInHandView.cardList = cards
    }

    companion object {
        private val PLAYER_NICK_KEY = "player_nick"
        fun getIntent(context: Context, playerNick: String) =
                Intent(context, GameActivity::class.java)
                        .putExtra(PLAYER_NICK_KEY, playerNick)
    }
}