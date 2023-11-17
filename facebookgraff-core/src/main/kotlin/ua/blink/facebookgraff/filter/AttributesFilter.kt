package ua.blink.facebookgraff.filter

import ua.blink.facebookgraff.annotation.FilterOrder
import ua.blink.facebookgraff.dsl.ButtonsFactory
import ua.blink.facebookgraff.dto.Message
import ua.blink.facebookgraff.dto.request.keyboard.InlineUrlReplyKeyboard
import ua.blink.facebookgraff.dto.request.keyboard.MarkupInlinedReplyKeyboard
import ua.blink.facebookgraff.filter.FilterOrders.Companion.ATTRIBUTES_FILTER_ORDER

/**
 * callback buttons solution added to support custom callback in both facebook and facebook (facebook only supports buttons in the latest message hence we do not need to try and support multiple active buttons
 */
@FilterOrder(ATTRIBUTES_FILTER_ORDER)
class AttributesFilter(
    private val buttonsFactory: ButtonsFactory
) : Filter {

    override suspend fun handleMessage(message: Message, chain: FilterChain) {
        buttonsFactory.getButtonsRequest(message.chatId)?.let { buttonsRequest ->
            val button =
                (buttonsRequest.buttons as? MarkupInlinedReplyKeyboard)?.buttons?.firstOrNull {
                    (it as? InlineUrlReplyKeyboard)?.text?.contains(
                        message.text ?: "",
                        ignoreCase = true
                    ) == true
                }
            if ((button as? InlineUrlReplyKeyboard)?.callbackData != null) {
                chain.doFilter(message.copy(text = button.callbackData))
            } else {
                chain.doFilter(message)
            }
        } ?: chain.doFilter(message)
    }
}