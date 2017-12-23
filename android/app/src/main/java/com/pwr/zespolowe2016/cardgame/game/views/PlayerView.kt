package com.pwr.zespolowe2016.cardgame.game.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.pwr.zespolowe2016.cardgame.R
import com.pwr.zespolowe2016.cardgame.other.DialogCreator
import com.pwr.zespolowe2016.cardgame.other.Lazy
import com.pwr.zespolowe2016.cardgame.other.bindString
import com.pwr.zespolowe2016.cardgame.other.bindView
import com.pwr.zespolowe2016.cardgame.other.requiredString

class PlayerView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    var playerName: String = ""
        set(value) {
            playerNameView.text = value
            field = value
        }
    var cardsCount: Int = 0
        set(value) {
            cardsCountView.text = cardsCountStringFormat.format(value.toString())
            field = value
        }
    var pointsCount: Int = 0
        set(value) {
            pointsCountView.text = pointsCountStringFormat.format(value.toString())
            field = value
        }
    var lifesCount: Int = 0
        set(value) {
            lifesCountView.text = lifesCountStringFormat.format(value.toString())
            field = value
        }

    val playerNameView: TextView by bindView(R.id.playerName)
    val cardsCountView: TextView by bindView(R.id.cardsCount)
    val pointsCountView: TextView by bindView(R.id.pointsCount)
    val lifesCountView: TextView by bindView(R.id.lifesCount)

    private val cardsCountStringFormat: String by bindString(R.string.player_cards_count_format)
    private val pointsCountStringFormat: String by bindString(R.string.player_points_count_format)
    private val lifesCountStringFormat: String by bindString(R.string.player_lifes_count_format)

    init {
        inflate(context, R.layout.player_in_game_view, this)
        initializeViewValues()
    }

    private fun initializeViewValues() {
        cardsCount = 0
        pointsCount = 0
        lifesCount = 0
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}