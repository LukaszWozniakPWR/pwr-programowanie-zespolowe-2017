package com.pwr.zespolowe2016.cardgame.game.cards.miniature_cards

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class MiniatureCardView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val photoView: ImageView by bindView(R.id.card_view_photo)
    private val pointsView: TextView by bindView(R.id.card_view_points)
    private val nameView: TextView by bindView(R.id.card_view_name)

    val layoutId: Int =  R.layout.miniature_card_view

    init {
        initialize()
    }

    private fun initialize() {
        inflate(context, layoutId, this)
    }

    fun displayCard(card: Card) {
        //TODO photoView
        val cardClass = card.cardClass
        photoView.setImageResource(cardClass.cardImage)
        pointsView.text = cardClass.basePoints.toString()
        nameView.text = context.getString(cardClass.cardName)
    }
}