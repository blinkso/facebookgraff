package ua.blink.facebookgraff.dto.request

import ua.blink.facebookgraff.dto.request.keyboard.InlineUrlReplyKeyboard
import ua.blink.facebookgraff.dto.request.keyboard.MarkupInlinedReplyKeyboard

class MarkdownMessage(
    text: String,
    vararg replies: String,
    chooseActionButton: String? = null,
    chatId: String = "",
    to: String = ""
) : MessageSendRequest(
    chatId = chatId,
    to = to,
    text = text,
    replyMarkup = if (replies.isNotEmpty()) {
        val inlines = replies.map { reply ->
            InlineUrlReplyKeyboard(
                text = reply,
                callbackData = reply
            )
        }
        MarkupInlinedReplyKeyboard(
            inlines = inlines,
            chooseActionButton = chooseActionButton
        )
    } else {
        null
    }
)