package ua.blink.facebookgraff.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import ua.blink.facebookgraff.dto.request.keyboard.ReplyKeyboard

@JsonInclude(JsonInclude.Include.NON_NULL)
abstract class SendRequest(
    @JsonIgnore
    var chatId: String,

    @get:JsonProperty("buttons")
    val buttons: ReplyKeyboard? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SendRequest) return false

        if (chatId != other.chatId) return false
        if (buttons != other.buttons) return false

        return true
    }

    override fun hashCode(): Int {
        var result = chatId.hashCode()
        result = 31 * result + buttons.hashCode()
        return result
    }

}