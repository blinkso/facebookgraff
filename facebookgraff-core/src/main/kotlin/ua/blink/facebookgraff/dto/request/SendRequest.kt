package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ua.blink.facebookgraff.dto.request.keyboard.ReplyKeyboard

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class SendRequest(
    @JsonIgnore
    var chatId: String,

    @JsonIgnore
    var to: String,

    @get:JsonProperty("buttons")
    val buttons: ReplyKeyboard? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SendRequest

        if (chatId != other.chatId) return false
        if (to != other.to) return false
        return buttons == other.buttons
    }

    override fun hashCode(): Int {
        var result = chatId.hashCode()
        result = 31 * result + to.hashCode()
        result = 31 * result + (buttons?.hashCode() ?: 0)
        return result
    }
}