package ua.blink.facebookgraff.dsl

import org.springframework.stereotype.Component
import ua.blink.facebookgraff.dto.request.MessageSendRequest

@Component
class DefaultButtonsFactory : ButtonsFactory {

    private val buttonRequests: MutableMap<String, MessageSendRequest> = hashMapOf()

    override fun addButtonsRequest(sendRequest: MessageSendRequest) {
        buttonRequests[sendRequest.chatId] = sendRequest
    }

    override fun getButtonsRequest(chatId: String): MessageSendRequest? {
        return buttonRequests[chatId]
    }
}