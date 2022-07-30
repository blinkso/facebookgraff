package me.ruslanys.telegraff.core.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class TelegramUpdate(
    @JsonProperty("update_id")
    val id: Long,

    @JsonProperty("callback_query")
    val callbackQuery: TelegramCallbackQuery?,

    @JsonProperty("message")
    val message: TelegramMessage?,

    @JsonProperty("edited_message")
    val editedMessage: TelegramMessage?,

    @JsonProperty("channel_post")
    val channelPost: TelegramMessage?,

    @JsonProperty("pre_checkout_query")
    val preCheckoutQuery: TelegramPreCheckoutQuery?,

    @JsonProperty("edited_channel_post")
    val editedChannelPost: TelegramMessage?
)