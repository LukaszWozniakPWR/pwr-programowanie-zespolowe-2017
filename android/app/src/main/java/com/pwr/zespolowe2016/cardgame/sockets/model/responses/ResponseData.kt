package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.google.gson.annotations.SerializedName

open class ResponseData(
        @SerializedName("success") val data_success: Boolean? = null
) {
    fun asSetNickname() = SetNicknameResponseData(data_success ?: false)
}