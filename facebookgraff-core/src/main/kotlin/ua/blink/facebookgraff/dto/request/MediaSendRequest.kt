package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

abstract class MediaSendRequest(
    chatId: String,

    @get:JsonProperty("caption")
    val caption: String? = null,
) : SendRequest(chatId, null)