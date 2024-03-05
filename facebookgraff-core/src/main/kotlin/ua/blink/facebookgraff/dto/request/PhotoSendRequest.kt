package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

open class PhotoSendRequest(
    chatId: String = "",

    to: String = "",

    @get:JsonProperty("photo")
    val photo: ByteArray,

    caption: String? = null
) : MediaSendRequest(chatId = chatId, to = to, caption = caption) {

    override fun toString(): String {
        return "PhotoSendRequest(photo=${photo.contentToString()})"
    }
}