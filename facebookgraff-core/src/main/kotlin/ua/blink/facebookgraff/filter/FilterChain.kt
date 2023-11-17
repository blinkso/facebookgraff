package ua.blink.facebookgraff.filter

import ua.blink.facebookgraff.dto.Message

interface FilterChain {
    suspend fun doFilter(message: Message)
}