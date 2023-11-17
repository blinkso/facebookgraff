package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdateMedia(
    @JsonProperty("Sid")
    val sid: String?,

    @JsonProperty("Filename")
    val filename: String?,

    @JsonProperty("ContentType")
    val contentType: String?,

    @JsonProperty("Size")
    val size: Long?
)