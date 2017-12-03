package com.pwr.zespolowe2016.cardgame.sockets.model.responses.setnickname

import com.google.gson.annotations.SerializedName

data class SetNicknameResponse(
        @SerializedName("success") val success: Boolean
) {
    companion object {
        fun empty() = SetNicknameResponse(
                false
        )
    }
}