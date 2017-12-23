package com.pwr.zespolowe2016.cardgame.game

import android.content.Context
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.recyclerview.BaseViewHolder

class HorizontalCardsViewHolder(
        private val context: Context,
        view: View
) : BaseViewHolder<Card>(view) {

    private val cardView: CardView by bindView(R.id.card_view)

    override fun displayItem(itemType: Card) {
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