package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Photo(
    @JsonProperty("category")
    val category: String,

    @JsonProperty("filename")
    val filename: String?,

    @JsonProperty("size")
    val size: Long,

    @JsonProperty("content_type")
    val contentType: String,

    @JsonProperty("sid")
    val sid: String
)