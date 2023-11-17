package ua.blink.facebookgraff.dto.request.keyboard

import com.fasterxml.jackson.annotation.JsonProperty

class MarkupInlinedReplyKeyboard(
    inlines: List<InlineUrlReplyKeyboard>,
    chooseActionButton: String? = null
) : ReplyKeyboard() {

    @get:JsonProperty("buttons")
    val buttons: List<ReplyKeyboard> = inlines
        .toList()
        .let { keyboardButtons ->
            if (chooseActionButton != null) {
                keyboardButtons.plusElement(ActionReplyKeyboard(text = chooseActionButton))
            } else {
                keyboardButtons
            }
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false

        other as MarkupInlinedReplyKeyboard

        if (buttons != other.buttons) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + buttons.hashCode()
        return result
    }
}