package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

open class VoiceSendRequest(
    chatId: String,

    @get:JsonProperty("voice")
    val voice: ByteArray,

    caption: String? = null
) : MediaSendRequest(chatId, caption) {

    override fun toString(): String {
        return "VoiceSendRequest(voice=${voice.contentToString()})"
    }
}