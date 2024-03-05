package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

open class VoiceSendRequest(
    chatId: String,

    to: String,

    @get:JsonProperty("voice")
    val voice: ByteArray,

    caption: String? = null
) : MediaSendRequest(chatId = chatId, to = to, caption = caption) {

    override fun toString(): String {
        return "VoiceSendRequest(voice=${voice.contentToString()})"
    }
}