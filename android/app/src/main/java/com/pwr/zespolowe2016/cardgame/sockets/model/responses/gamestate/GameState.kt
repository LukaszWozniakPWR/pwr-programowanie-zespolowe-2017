package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.playerlist.Player

data class GameState(
        @SerializedName("opponent") val opponent: Player,
        @SerializedName("self") val you: Player,
        @SerializedName("opponentState") val opponentState: OpponentState,
        @SerializedName("selfState") val yourState: YourState,
        @SerializedName("turn") val turn: Turn
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readParcelable(Player::class.java.classLoader),
            parcel.readParcelable(Player::class.java.classLoader),
            parcel.readParcelable(OpponentState::class.java.classLoader),
            parcel.readParcelable(YourState::class.java.classLoader),
            parcel.readSerializable() as Turn
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(opponent, flags)
        parcel.writeParcelable(you, flags)
        parcel.writeParcelable(opponentState, flags)
        parcel.writeParcelable(yourState, flags)
        parcel.writeSerializable(turn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<GameState> {
        override fun createFromParcel(parcel: Parcel): GameState {
            return GameState(parcel)
        }

        override fun newArray(size: Int): Array<GameState?> {
            return arrayOfNulls(size)
        }
    }
}