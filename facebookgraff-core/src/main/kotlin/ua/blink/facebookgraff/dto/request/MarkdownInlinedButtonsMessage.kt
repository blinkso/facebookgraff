package ua.blink.facebookgraff.dto.request

import ua.blink.facebookgraff.dto.request.keyboard.InlineUrlReplyKeyboard
import ua.blink.facebookgraff.dto.request.keyboard.MarkupInlinedReplyKeyboard

class MarkdownInlinedButtonsMessage(
    text: String,
    vararg inlines: InlineUrlReplyKeyboard,
    chatId: String = "",
    to: String = "",
    chooseActionButton: String? = null
) : MessageSendRequest(
        chatId = chatId,
        to = to,
        text = text,
        replyMarkup = if (inlines.isNotEmpty()) {
            MarkupInlinedReplyKeyboard(
                inlines = inlines.toList(),
                chooseActionButton = chooseActionButton
            )
        } else {
            null
        }
    )