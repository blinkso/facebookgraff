package ua.blink.facebookgraff.client

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ua.blink.facebookgraff.component.ConversationApi
import ua.blink.facebookgraff.dto.Message
import ua.blink.facebookgraff.dto.Update
import ua.blink.facebookgraff.event.UpdateEvent

@RestController
class WebhookClient(
    private val webhookUrl: String,
    private val conversationApi: ConversationApi,
    private val publisher: ApplicationEventPublisher,
    private val objectMapper: ObjectMapper
) : Client {

    @PostConstruct
    override fun start() {
        log.info("Client: WEBHOOK")
        log.info("Setting webhook at: {}", webhookUrl)
        conversationApi.setWebhook(webhookUrl)
    }

    @PreDestroy
    override fun shutdown() {
        log.info("Removing webhook")
        conversationApi.removeWebhook()
    }

    override fun onUpdate(update: Message) {
        log.info("Got a new event: {}", update)
        publisher.publishEvent(UpdateEvent(this, update))
    }

    @RequestMapping("#{whatsappProperties.getWebhookEndpointUrl()}")
    fun update(@RequestBody update: Update): ResponseEntity<String> {
        onUpdate(update.getMessage(objectMapper))
        return ResponseEntity.ok("ok")
    }

    companion object {
        private val log = LoggerFactory.getLogger(WebhookClient::class.java)
    }

}