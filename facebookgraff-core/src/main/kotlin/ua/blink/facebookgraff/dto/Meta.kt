package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty


data class Meta(
    @JsonProperty("page")
    val page: Int,

    @JsonProperty("page_size")
    val pageSize: Int,

    @JsonProperty("first_page_url")
    val firstPageUrl: String,

    @JsonProperty("previous_page_url")
    val previousPageUrl: String?,

    @JsonProperty("url")
    val url: String,

    @JsonProperty("next_page_url")
    val nextPageUrl: String?,

    @JsonProperty("key")
    val key: String
)