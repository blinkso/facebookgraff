package ua.blink.facebookgraff.autoconfigure.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

@Component("facebookProperties")
@ConfigurationProperties(prefix = "facebook", ignoreUnknownFields = false)
class Properties {

    /**
     * facebook Bot API Access Key.
     */
    var accessKey: String = ""

    /**
     * facebook Bot API Account Key.
     */
    var accountSid: String = ""

    /**
     * facebook Bot API Service Key.
     */
    var serviceSid: String = ""

    /**
     * WhatsApp Bot API Messaging Key.
     */
    var messagingSid: String = ""

    /**
     * Button templates up to three buttons
     */
    var buttonTemplate: List<String> = listOf()

    /**
     * List templates up to three buttons
     */
    var listTemplate: List<String> = listOf()

    /**
     * facebook updates mode.
     */
    var mode = FacebookMode.POLLING

    /**
     * Webhook base URL.
     * For example, https://localhost:8443.
     */
    var webhookBaseUrl: String? = null

    /**
     * Webhook endpoint url.
     * For example, /facebook.
     */
    var webhookEndpointUrl: String = "/facebook/" + UUID.randomUUID().toString()

    /**
     * Path where handlers declaration stored.
     */
    var handlersPath = "handlers"

    /**
     * UnresolvedMessageFilter properties.
     */
    var unresolvedFilter = UnresolvedMessageFilterProperties()


    fun getWebhookUrl(): String = "$webhookBaseUrl$webhookEndpointUrl"


    class UnresolvedMessageFilterProperties {
        /**
         * Enable UnresolvedMessageFilter.
         */
        var enabled: Boolean = true
    }

}
