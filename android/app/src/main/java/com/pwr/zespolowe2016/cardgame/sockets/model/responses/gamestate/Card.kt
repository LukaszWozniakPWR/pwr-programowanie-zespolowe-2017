package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class Card(
        @SerializedName("name") val cardClass: CardClass,
        @SerializedName("strength") val actualStrength: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readSerializable() as CardClass,
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeSerializable(cardClass)
        parcel.writeInt(actualStrength)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Card> {
        override fun createFromParcel(parcel: Parcel): Card {
            return Card(parcel)
        }

        override fun newArray(size: Int): Array<Card?> {
            return arrayOfNulls(size)
        }
    }
}