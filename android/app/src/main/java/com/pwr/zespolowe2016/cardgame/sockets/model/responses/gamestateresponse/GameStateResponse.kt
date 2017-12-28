package com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestateresponse

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.gamestate.GameState

data class GameStateResponse(
        @SerializedName("success") val success: Boolean,
        @SerializedName("game") val gameState: GameState
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readByte() != 0.toByte(),
            parcel.readParcelable(GameState::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (success) 1 else 0)
        parcel.writeParcelable(gameState, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<GameStateResponse> {
        override fun createFromParcel(parcel: Parcel): GameStateResponse {
            return GameStateResponse(parcel)
        }

        override fun newArray(size: Int): Array<GameStateResponse?> {
            return arrayOfNulls(size)
        }
    }
}