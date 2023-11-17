package ua.blink.facebookgraff.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class Media(
    @JsonProperty("sid")
    val sid: String,

    @JsonProperty("service_sid")
    val serviceSid: String?,

    @JsonProperty("date_created")
    val dateCreated: String?,

    @JsonProperty("date_upload_updated")
    val dateUploadUpdated: String?,

    @JsonProperty("date_updated")
    val dateUpdated: String?,

    @JsonProperty("links")
    val links: Map<String?, String?>,

    @JsonProperty("size")
    val size: Long?,

    @JsonProperty("content_type")
    val contentType: String?,

    @JsonProperty("filename")
    val filename: String?,

    @JsonProperty("author")
    val author: String?,

    @JsonProperty("category")
    val category: String?,

    @JsonProperty("message_sid")
    val messageSid: String?,

    @JsonProperty("channel_sid")
    val channelSid: String?,

    @JsonProperty("url")
    val url: String?,

    @JsonProperty("is_multipart_upstream")
    val isMultipartUpstream: Boolean?
)