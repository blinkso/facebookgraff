package ua.blink.facebookgraff.autoconfigure

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.validation.Validator
import ua.blink.facebookgraff.autoconfigure.property.Properties
import ua.blink.facebookgraff.autoconfigure.property.PropertiesValidator
import ua.blink.facebookgraff.client.PollingClient
import ua.blink.facebookgraff.client.WebhookClient
import ua.blink.facebookgraff.component.ConversationApi

/**
 * Enable Auto-Configuration for Telegraff.
 *
 */
@Configuration
@ConditionalOnClass(PollingClient::class, WebhookClient::class, ConversationApi::class)
@EnableConfigurationProperties(Properties::class)
@Import(TelegraffNonWebConfiguration::class, TelegraffServletWebConfiguration::class)
class TelegraffAutoConfiguration {

    companion object {
        @Bean
        fun configurationPropertiesValidator(): Validator {
            return PropertiesValidator()
        }
    }

}