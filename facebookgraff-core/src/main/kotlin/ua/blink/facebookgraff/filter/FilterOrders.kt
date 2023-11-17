package ua.blink.facebookgraff.filter

class FilterOrders {

    companion object {
        const val ATTRIBUTES_FILTER_ORDER = -2
        const val DEEPLINK_FILTER_ORDER = -1
        const val CANCEL_FILTER_ORDER = 0
        const val HANDLERS_FILTER_ORDER = 1
        const val UNRESOLVED_FILTER_ORDER = Integer.MAX_VALUE
    }
}