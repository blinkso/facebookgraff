package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

open class PhotoSendRequest(
    chatId: String = "",

    @get:JsonProperty("photo")
    val photo: ByteArray,

    caption: String? = null
) : MediaSendRequest(chatId, caption) {

    override fun toString(): String {
        return "PhotoSendRequest(photo=${photo.contentToString()})"
    }
}