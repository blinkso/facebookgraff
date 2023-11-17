package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Conversation(
    @JsonProperty("unique_name")
    val uniqueName: String?,

    @JsonProperty("date_updated")
    val dateUpdated: String,

    @JsonProperty("friendly_name")
    val friendlyName: String?,

    @JsonProperty("timers")
    val timers: Map<String, Any>,

    @JsonProperty("account_sid")
    val accountSid: String,

    @JsonProperty("url")
    val url: String,

    @JsonProperty("state")
    val state: String,

    @JsonProperty("date_created")
    val dateCreated: String,

    @JsonProperty("messaging_service_sid")
    val messagingServiceSid: String,

    @JsonProperty("sid")
    val chatId: String,

    @JsonProperty("attributes")
    val attributes: String,

    @JsonProperty("bindings")
    val bindings: Any?,

    @JsonProperty("chat_service_sid")
    val chatServiceSid: String,
)