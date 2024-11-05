package ua.blink.facebookgraff.filter

import ua.blink.facebookgraff.annotation.FilterOrder
import ua.blink.facebookgraff.dsl.ButtonsFactory
import ua.blink.facebookgraff.dto.Message
import ua.blink.facebookgraff.dto.request.keyboard.InlineUrlReplyKeyboard
import ua.blink.facebookgraff.dto.request.keyboard.MarkupInlinedReplyKeyboard
import ua.blink.facebookgraff.filter.FilterOrders.Companion.ATTRIBUTES_FILTER_ORDER

/**
 * callback buttons solution added to support custom callback in both facebook and whatsapp (facebook only supports buttons in the latest message hence we do not need to try and support multiple active buttons
 */
@FilterOrder(ATTRIBUTES_FILTER_ORDER)
class AttributesFilter(
    private val buttonsFactory: ButtonsFactory
) : Filter {

    override suspend fun handleMessage(message: Message, chain: FilterChain) {
        // Get all button requests for the chatId
        val chatId = message.chatId ?: ""
        val messageText = message.text ?: ""

        // Check all button requests for matching buttons
        buttonsFactory.getButtonsRequests(chatId).asReversed().forEach { buttonsRequest ->
            val button = (buttonsRequest.buttons as? MarkupInlinedReplyKeyboard)?.buttons?.firstOrNull {
                (it as? InlineUrlReplyKeyboard)?.text?.contains(messageText, ignoreCase = true) == true
            }

            if ((button as? InlineUrlReplyKeyboard)?.callbackData != null) {
                return chain.doFilter(message.copy(text = button.callbackData))
            }
        }

        chain.doFilter(message)
    }
}