package com.pwr.zespolowe2016.cardgame.game.cards

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class HorizontalCardsViewHolder(
        private val context: Context,
        view: View,
        private val onClickListener: (Card) -> Unit
) : BaseViewHolder<Card>(view) {

    private val cardView: CardView by bindView(R.id.card_view)

    override fun displayItem(itemType: Card) {
        cardView.setOnClickListener { onClickListener.invoke(itemType) }
        setCardWidth()
        cardView.displayCard(itemType)
    }

    private fun setCardWidth() {
        val windowsManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val width = windowsManager.defaultDisplay.width
        val height = windowsManager.defaultDisplay.height
        cardView.layoutParams = LinearLayout.LayoutParams(width / 2, height / 2)
    }
}