package ua.blink.facebookgraff.util

import org.springframework.core.annotation.AnnotationUtils
import ua.blink.facebookgraff.annotation.FilterOrder

object FilterOrderUtil {
    fun getOrder(type: Class<*>): Int {
        return AnnotationUtils.findAnnotation(
            type,
            FilterOrder::class.java
        )?.value ?: 0
    }
}