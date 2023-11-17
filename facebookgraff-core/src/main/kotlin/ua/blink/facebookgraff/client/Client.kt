package ua.blink.facebookgraff.client

import ua.blink.facebookgraff.dto.Message

interface Client {
    fun start()

    fun shutdown()

    fun onUpdate(update: Message)
}