package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.R

enum class CardClass(
        val basePoints: Int,
        val rowInfo: RowInfo,
        @StringRes val cardName: Int,
        @StringRes val cardDescription: Int,
        @DrawableRes val cardImage: Int
) {
    /* todo NO CARDS YET, JACOPO SYGA WILL DO */
    @SerializedName("ARCHER") ARCHER(5, RowInfo.ARCHERS, R.string.nickname_hint, R.string.nickname_incorrect, R.drawable.ic_dissatisfied_512)
}