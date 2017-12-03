package com.pwr.zespolowe2016.cardgame.sockets.model.responses

import com.google.gson.annotations.SerializedName

data class SetNicknameResponseData(
        @SerializedName("success") val success: Boolean
) : ResponseData(data_success = success)