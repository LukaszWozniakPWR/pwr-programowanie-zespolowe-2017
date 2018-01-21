package com.pwr.zespolowe2016.cardgame.game.cards

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card

class CardView : LinearLayout {

    private val photoView: ImageView by bindView(R.id.card_view_photo)
    private val pointsView: TextView by bindView(R.id.card_view_points)
    private val nameView: TextView by bindView(R.id.card_view_name)
    private val descriptionView: TextView by bindView(R.id.card_view_description)
    private val attributesRecyclerView: RecyclerView by bindView(R.id.attributesRecyclerView)

    private val layoutId: Int =  R.layout.card_view

    private val attributesAdapter = AttributesAdapter()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initialize()
    }

    private fun initialize() {
        inflate(context, layoutId, this)
        orientation = VERTICAL
        attributesRecyclerView.adapter = attributesAdapter
    }

    fun displayCard(card: Card) {
        //TODO photoView
        val cardClass = card.cardClass
        photoView.setImageResource(cardClass.cardImage)
        pointsView.text = card.actualStrength.toString()
        nameView.text = context.getString(cardClass.cardName)
        descriptionView.text = context.getString(cardClass.cardDescription)
        attributesAdapter.setData(card.cardClass.attributes)
    }
}