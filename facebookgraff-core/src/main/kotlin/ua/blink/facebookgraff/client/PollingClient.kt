package ua.blink.facebookgraff.client

import jakarta.annotation.PreDestroy
import org.slf4j.LoggerFactory
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.ApplicationListener
import ua.blink.facebookgraff.component.ConversationApi
import ua.blink.facebookgraff.dto.Message
import ua.blink.facebookgraff.event.UpdateEvent
import java.util.concurrent.TimeUnit

class PollingClient(
    private val conversationApi: ConversationApi,
    private val publisher: ApplicationEventPublisher
) : Client, ApplicationListener<ApplicationReadyEvent> {

    private val client = Client()

    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        start()
    }

    override fun start() {
        log.info("WhatsApp client: POLLING")
        client.start()
    }

    @PreDestroy
    override fun shutdown() {
        client.interrupt()
        client.join()
    }

    override fun onUpdate(update: Message) {
        log.info("Got a new event: {}", update)
        publisher.publishEvent(UpdateEvent(this, update))
    }

    private inner class Client : Thread("PollingClient") {

        private var offset: String = ""

        override fun run() {
            while (!isInterrupted) {
                try {
                    val updates =
                        conversationApi.getUpdates(offset = offset.takeIf { it != "" }, timeout = POLLING_TIMEOUT)
                    if (updates.isEmpty()) {
                        continue
                    }

                    // Find the index of the update with the specified offset SID
                    val offsetIndex = updates.indexOfFirst { it.sid == offset }

                    // If the offset SID is found, process updates after it; otherwise, process all updates
                    val updatesToProcess = if (offsetIndex != -1) updates.drop(offsetIndex + 1) else updates

                    // Publish updates
                    for (update in updatesToProcess) {
                        onUpdate(update)
                    }

                    // Update the offset to the SID of the last update processed, if any
                    offset = updatesToProcess.lastOrNull()?.sid ?: updates.last().sid
                } catch (e: Exception) {
                    log.error("Exception during polling messages", e)
                    TimeUnit.SECONDS.sleep(POLLING_TIMEOUT)
                }
            }
        }

    }

    companion object {
        private val log = LoggerFactory.getLogger(PollingClient::class.java)
        private const val POLLING_TIMEOUT = 10L
    }


}