package ua.blink.facebookgraff.dsl

import java.util.concurrent.ConcurrentHashMap

class HandlerState(
    val chatId: String,
    val contact: String,
    val username: String,
    val handler: Handler
) {

    var currentStep: Step<*>? = handler.getInitialStep()
    val answers: MutableMap<String, Any> = ConcurrentHashMap()
    val attributes: MutableMap<String, Any> = ConcurrentHashMap()

}