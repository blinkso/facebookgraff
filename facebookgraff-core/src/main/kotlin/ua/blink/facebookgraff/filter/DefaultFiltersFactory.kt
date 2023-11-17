package ua.blink.facebookgraff.filter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ua.blink.facebookgraff.event.UpdateEvent
import ua.blink.facebookgraff.util.FilterOrderUtil
import kotlin.coroutines.CoroutineContext

class DefaultFiltersFactory(filters: List<Filter>) :
    FiltersFactory,
    FilterProcessor,
    CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val filters: List<Filter> = filters.sortedBy {
        FilterOrderUtil.getOrder(it::class.java)
    }

    override fun getFilters(): List<Filter> {
        return filters
    }

    override fun process(event: UpdateEvent) {
        launch {
            if (event.update != null) { // only new messages are supported
                val chain = DefaultFilterChain(filters)
                chain.doFilter(event.update)
            }
        }
    }

}