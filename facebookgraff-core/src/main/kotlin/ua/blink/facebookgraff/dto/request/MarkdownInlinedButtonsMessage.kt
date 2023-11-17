package ua.blink.facebookgraff.dto.request

import ua.blink.facebookgraff.dto.request.keyboard.InlineUrlReplyKeyboard
import ua.blink.facebookgraff.dto.request.keyboard.MarkupInlinedReplyKeyboard

class MarkdownInlinedButtonsMessage(
    text: String,
    vararg inlines: InlineUrlReplyKeyboard,
    chatId: String = "",
    chooseActionButton: String? = null
) :
    MessageSendRequest(
        chatId = chatId,
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