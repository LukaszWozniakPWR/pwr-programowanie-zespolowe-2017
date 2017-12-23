package com.pwr.zespolowe2016.cardgame.game

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView

class CardsDialog(context: Context,
                  private val cards: List<Card>
) : AlertDialog(context) {

    private val cardsList: RecyclerView by bindView(R.id.cards_dialog_list)
    private val closeButton: Button by bindView(R.id.cards_dialog_close_button)

    private val cardsAdapter = CardsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cards_dialog)
        closeButton.setOnClickListener { dismiss() }
        cardsList.adapter = cardsAdapter
       // loadCards()
    }

    fun loadCards(cards: List<Card>) {
        cardsAdapter.setData(cards)
    }
}