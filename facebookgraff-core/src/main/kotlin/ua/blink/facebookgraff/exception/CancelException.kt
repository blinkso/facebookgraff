package ua.blink.facebookgraff.exception

import ua.blink.facebookgraff.dto.request.SendRequest

class CancelException(val messageRequest: SendRequest) : HandlerException("Handler cancelled")