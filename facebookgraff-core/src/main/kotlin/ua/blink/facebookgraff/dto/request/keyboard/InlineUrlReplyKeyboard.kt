package ua.blink.facebookgraff.dto.request.keyboard

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonInclude(JsonInclude.Include.NON_NULL)
class InlineUrlReplyKeyboard(
    @get:JsonProperty("title")
    val text: String,
    @get:JsonProperty("url")
    val url: String? = null,
    @get:JsonProperty("payload")
    val callbackData: String? = null
) : ReplyKeyboard() {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as InlineUrlReplyKeyboard

        if (text != other.text) return false
        if (url != other.url) return false
        if (callbackData != other.callbackData) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + callbackData.hashCode()
        return result
    }
}