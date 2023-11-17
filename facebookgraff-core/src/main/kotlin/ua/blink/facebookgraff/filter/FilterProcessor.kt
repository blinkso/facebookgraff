package ua.blink.facebookgraff.filter

import org.springframework.context.event.EventListener
import ua.blink.facebookgraff.event.UpdateEvent

interface FilterProcessor {
    @EventListener(UpdateEvent::class)
    fun process(event: UpdateEvent)
}