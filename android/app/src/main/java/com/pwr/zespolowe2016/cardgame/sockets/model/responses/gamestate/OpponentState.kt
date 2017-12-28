package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class OpponentState(
        @SerializedName("passed") val passed: Boolean,
        @SerializedName("score") val roundsWon: Int,
        @SerializedName("points") val points: Int,
        @SerializedName("frontRow") val swordsRow: Row,
        @SerializedName("middleRow") val archersRow: Row,
        @SerializedName("rearRow") val catapultsRow: Row,
        @SerializedName("handLength") val numberOfCardsInHand: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(Row::class.java.classLoader),
            parcel.readParcelable(Row::class.java.classLoader),
            parcel.readParcelable(Row::class.java.classLoader),
            parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (passed) 1 else 0)
        parcel.writeInt(roundsWon)
        parcel.writeInt(points)
        parcel.writeParcelable(swordsRow, flags)
        parcel.writeParcelable(archersRow, flags)
        parcel.writeParcelable(catapultsRow, flags)
        parcel.writeInt(numberOfCardsInHand)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<OpponentState> {
        override fun createFromParcel(parcel: Parcel): OpponentState {
            return OpponentState(parcel)
        }

        override fun newArray(size: Int): Array<OpponentState?> {
            return arrayOfNulls(size)
        }
    }
}