package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Message(
    @JsonProperty("sid")
    val sid: String,

    @JsonProperty("account_sid")
    val accountSid: String,

    @JsonProperty("date_created")
    val date: String,

    @JsonProperty("chat_service_sid")
    val serviceSid: String?,

    @JsonProperty("conversation_sid")
    val chatId: String?,

    @JsonProperty("author")
    val user: String?,

    @JsonProperty("attributes")
    val attributes: String?,

    @JsonProperty("media")
    val photo: List<Photo>?,

    @JsonProperty("body")
    var text: String?,
) {

    // In case if it was a contact request question phone number of user contact will be returned and validation block will receive contact
    // for it's validation
    fun getMessageText(): String? {
        return if (photo != null) {
            photo.firstOrNull()?.sid ?: text
        } else {
            text
        }
    }
}