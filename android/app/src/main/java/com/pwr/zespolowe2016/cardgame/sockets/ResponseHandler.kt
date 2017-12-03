package com.pwr.zespolowe2016.cardgame.sockets

import com.pwr.zespolowe2016.cardgame.sockets.model.responses.Response
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.ResponseType.NICKNAME_RESPONSE
import com.pwr.zespolowe2016.cardgame.sockets.model.responses.SetNicknameResponseData

class ResponseHandler(private val callbacks: List<SocketAidlCallback> = emptyList()) {

    fun withCallbackAdded(callback: SocketAidlCallback): ResponseHandler {
        return if (callbacks.contains(callback)) this else ResponseHandler(callbacks.plus(callback))
    }

    fun withCallbackRemoved(callback: SocketAidlCallback): ResponseHandler {
        return ResponseHandler(callbacks.minus(callback))
    }

    fun handleResponse(response: Response) {
        when (response.type) {
            NICKNAME_RESPONSE -> handleSetNicknameResponse(response.responseData.asSetNickname())
        }
    }

    private fun handleSetNicknameResponse(data: SetNicknameResponseData) {
        callbacks.forEach { callback -> callback.onSetNicknameResponse(data.success) }
    }
}