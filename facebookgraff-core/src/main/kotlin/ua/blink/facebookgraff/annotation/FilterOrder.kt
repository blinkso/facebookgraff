package ua.blink.facebookgraff.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class FilterOrder(val value: Int)