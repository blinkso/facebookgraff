package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Response(
    @JsonProperty("meta")
    val meta: Meta,

    @JsonProperty("messages")
    val messages: List<Message>?,

    @JsonProperty("conversations")
    val conversations: List<Conversation>?
)