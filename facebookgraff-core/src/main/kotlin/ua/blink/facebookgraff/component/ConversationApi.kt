package ua.blink.facebookgraff.component

import ua.blink.facebookgraff.dto.Message
import ua.blink.facebookgraff.dto.request.DocumentSendRequest
import ua.blink.facebookgraff.dto.request.MessageSendRequest
import ua.blink.facebookgraff.dto.request.PhotoSendRequest
import ua.blink.facebookgraff.dto.request.VoiceSendRequest

interface ConversationApi {

    fun getUpdates(offset: String?, timeout: Long?): List<Message>

    fun getUpdates(offset: String?): List<Message> {
        return getUpdates(offset, null)
    }

    fun getUpdates(): List<Message> {
        return getUpdates(null)
    }

    fun getMessages(chatId: String): List<Message>

    fun getFileByPath(filePath: String): ByteArray

    fun setWebhook(url: String)

    fun removeWebhook()

    fun sendMessage(request: MessageSendRequest): Message

    fun sendPhoto(request: PhotoSendRequest): Message

    fun sendDocument(request: DocumentSendRequest): Message

    fun sendVoice(request: VoiceSendRequest): Message
}
