package com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("name") val name: String,
        @SerializedName("state") val status: PlayerStatus
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(), parcel.readSerializable() as PlayerStatus
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeSerializable(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}