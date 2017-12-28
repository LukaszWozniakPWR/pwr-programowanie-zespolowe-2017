package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.Card.CREATOR

data class Row(
        @SerializedName("elements") val battleCards: List<Card>,
        @SerializedName("effects") val activeEffects: List<Effect>,
        @SerializedName("points") val sumOfPoints: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.createTypedArrayList(Card),
            mutableListOf<Effect>().apply {
                val size: Int = parcel.readInt()
                for (i in 1..size) {
                    add(parcel.readSerializable() as Effect)
                }
            },
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(battleCards)
        parcel.writeInt(activeEffects.size)
        activeEffects.forEach { effect -> parcel.writeSerializable(effect) }
        parcel.writeInt(sumOfPoints)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Row> {
        override fun createFromParcel(parcel: Parcel): Row {
            return Row(parcel)
        }

        override fun newArray(size: Int): Array<Row?> {
            return arrayOfNulls(size)
        }
    }
}