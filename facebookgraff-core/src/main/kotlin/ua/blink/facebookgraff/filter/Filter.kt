package ua.blink.facebookgraff.filter

import ua.blink.facebookgraff.dto.Message

interface Filter {
    suspend fun handleMessage(message: Message, chain: FilterChain)
}