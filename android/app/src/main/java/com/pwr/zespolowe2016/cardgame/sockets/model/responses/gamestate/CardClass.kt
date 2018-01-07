package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.R

enum class CardClass(
        val basePoints: Int,
        val rowInfo: RowInfo,
        val fraction: Fraction,
        @StringRes val cardName: Int,
        @StringRes val cardDescription: Int,
        @DrawableRes val cardImage: Int
) {
    /* todo NO CARDS YET, JACOPO SYGA WILL DO */
    @SerializedName("BALDUR") BALDUR(10, RowInfo.SWORDS, Fraction.NILFGAARD, R.string.baldur_card_name, R.string.baldur_card_description, R.drawable.ic_dissatisfied_512),
    @SerializedName("ARCHER") ARCHER(5, RowInfo.ARCHERS, Fraction.NORTH, R.string.testArcher, R.string.testArcherDescription, R.drawable.ic_dissatisfied_512)
}