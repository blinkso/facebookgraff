package ua.blink.facebookgraff.dsl

import ua.blink.facebookgraff.dto.request.MessageSendRequest

interface ButtonsFactory {

    fun addButtonsRequest(sendRequest: MessageSendRequest)

    fun getButtonsRequest(chatId: String): MessageSendRequest?

}