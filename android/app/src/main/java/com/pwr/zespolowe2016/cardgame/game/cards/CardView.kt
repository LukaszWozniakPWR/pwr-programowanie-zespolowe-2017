package com.pwr.zespolowe2016.cardgame.game.cards

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.game.cards.Card
import com.pwr.zespolowe2016.cardgame.other.bindView

class CardView : LinearLayout {

    private val photoView: ImageView by bindView(R.id.card_view_photo)
    private val pointsView: TextView by bindView(R.id.card_view_points)
    private val nameView: TextView by bindView(R.id.card_view_name)
    private val typeView: TextView by bindView(R.id.card_view_type)
    private val descriptionView: TextView by bindView(R.id.card_view_description)

    private val layoutId: Int =  R.layout.card_view

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        inflate(context, layoutId, this)
        orientation = VERTICAL
    }

    fun displayCard(card: Card) {
        //TODO photoView
        pointsView.text = card.points.toString()
        nameView.text = card.name
        typeView.text = card.type.realName()
        descriptionView.text = card.description
    }
}