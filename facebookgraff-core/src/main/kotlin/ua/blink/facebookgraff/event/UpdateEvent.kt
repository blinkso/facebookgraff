package ua.blink.facebookgraff.event

import org.springframework.context.ApplicationEvent
import ua.blink.facebookgraff.dto.Message

class UpdateEvent(
    source: Any,
    val update: Message?
) : ApplicationEvent(source)