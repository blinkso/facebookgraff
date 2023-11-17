package ua.blink.facebookgraff.filter

interface FiltersFactory {
    fun getFilters(): List<Filter>
}