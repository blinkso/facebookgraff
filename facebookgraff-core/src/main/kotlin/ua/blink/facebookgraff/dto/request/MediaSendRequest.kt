package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonProperty

abstract class MediaSendRequest(
    chatId: String,

    to: String,

    @get:JsonProperty("caption")
    val caption: String? = null,
) : SendRequest(chatId = chatId, to = to, buttons = null)