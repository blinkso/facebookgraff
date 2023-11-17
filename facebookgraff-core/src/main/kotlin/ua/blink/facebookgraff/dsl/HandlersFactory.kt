package ua.blink.facebookgraff.dsl

interface HandlersFactory {
    fun getHandlers(): Map<String, Handler>
}