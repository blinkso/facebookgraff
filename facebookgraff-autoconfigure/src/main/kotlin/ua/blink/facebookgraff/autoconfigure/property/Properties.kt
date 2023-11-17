package ua.blink.facebookgraff.autoconfigure.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.util.*

@Component("whatsappProperties")
@ConfigurationProperties(prefix = "whatsapp", ignoreUnknownFields = false)
class Properties {

    /**
     * WhatsApp Bot API Access Key.
     */
    var accessKey: String = ""

    /**
     * WhatsApp Bot API Account Key.
     */
    var accountSid: String = ""

    /**
     * WhatsApp Bot API Service Key.
     */
    var serviceSid: String = ""

    /**
     * Button templates up to three buttons
     */
    var buttonTemplate: List<String> = listOf()

    /**
     * List templates up to three buttons
     */
    var listTemplate: List<String> = listOf()

    /**
     * WhatsApp updates mode.
     */
    var mode = WhatsAppMode.POLLING

    /**
     * Webhook base URL.
     * For example, https://localhost:8443.
     */
    var webhookBaseUrl: String? = null

    /**
     * Webhook endpoint url.
     * For example, /whatsapp.
     */
    var webhookEndpointUrl: String = "/whatsapp/" + UUID.randomUUID().toString()

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
