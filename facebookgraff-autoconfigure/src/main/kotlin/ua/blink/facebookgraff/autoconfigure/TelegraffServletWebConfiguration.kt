package ua.blink.facebookgraff.autoconfigure

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import ua.blink.facebookgraff.autoconfigure.property.Properties
import ua.blink.facebookgraff.client.Client
import ua.blink.facebookgraff.client.PollingClient
import ua.blink.facebookgraff.client.WebhookClient
import ua.blink.facebookgraff.component.ConversationApi
import ua.blink.facebookgraff.component.DefaultConversationApi
import ua.blink.facebookgraff.dsl.ButtonsFactory
import ua.blink.facebookgraff.dsl.DefaultButtonsFactory
import ua.blink.facebookgraff.dsl.DefaultHandlersFactory
import ua.blink.facebookgraff.dsl.HandlersFactory
import ua.blink.facebookgraff.filter.*

/**
 * Configuration for Telegraff when used in a servlet web context.
 *
 */
@Configuration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnClass(PollingClient::class, WebhookClient::class)
class TelegraffServletWebConfiguration(@Qualifier("facebookProperties") val properties: Properties) {

    @Bean
    fun objectMapper(): ObjectMapper {
        return Jackson2ObjectMapperBuilder()
            .modulesToInstall(KotlinModule.Builder().build())
            .build<ObjectMapper>()
            .apply { configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) }
    }

    @Bean
    @ConditionalOnMissingBean(ConversationApi::class)
    fun api(): ConversationApi {
        return DefaultConversationApi(
            accessKey = properties.accessKey,
            accountSid = properties.accountSid,
            serviceSid = properties.serviceSid,
            buttonTemplate = properties.buttonTemplate,
            listTemplate = properties.listTemplate
        )
    }

    @Bean
    @ConditionalOnMissingBean(name = ["facebookProperties"])
    fun properties(): Properties = properties

    // region Clients

    @Bean
    @ConditionalOnMissingBean(Client::class)
    @ConditionalOnProperty(name = ["facebook.mode"], havingValue = "polling", matchIfMissing = true)
    fun pollingClient(
        conversationApi: ConversationApi,
        publisher: ApplicationEventPublisher
    ): PollingClient {
        return PollingClient(conversationApi = conversationApi, publisher = publisher)
    }

    @Bean
    @ConditionalOnMissingBean(Client::class)
    @ConditionalOnProperty(name = ["facebook.mode"], havingValue = "webhook")
    fun webhookClient(
        objectMapper: ObjectMapper,
        conversationApi: ConversationApi,
        publisher: ApplicationEventPublisher
    ): WebhookClient {
        return WebhookClient(
            webhookUrl = properties.getWebhookUrl(),
            conversationApi = conversationApi,
            publisher = publisher,
            objectMapper = objectMapper
        )
    }

    // endregion

    @Bean
    @ConditionalOnMissingBean(HandlersFactory::class)
    fun handlersFactory(context: GenericApplicationContext): DefaultHandlersFactory {
        return DefaultHandlersFactory(handlersPath = properties.handlersPath, context = context)
    }

    // region Filters

    @Bean
    @ConditionalOnMissingBean(FiltersFactory::class, FilterProcessor::class)
    fun filtersFactory(filters: List<Filter>): DefaultFiltersFactory {
        return DefaultFiltersFactory(filters)
    }

    @Bean
    @ConditionalOnMissingBean(ButtonsFactory::class)
    fun buttonsFactory(
        conversationApi: ConversationApi,
    ): DefaultButtonsFactory {
        return DefaultButtonsFactory()
    }

    @Bean
    @ConditionalOnMissingBean(HandlersFilter::class)
    fun handlersFilter(
        conversationApi: ConversationApi,
        buttonsFactory: ButtonsFactory,
        handlersFactory: HandlersFactory
    ): HandlersFilter {
        return HandlersFilter(
            conversationApi = conversationApi,
            buttonsFactory = buttonsFactory,
            handlersFactory = handlersFactory
        )
    }

    @Bean
    @ConditionalOnMissingBean(AttributesFilter::class)
    fun attributesFilter(
        buttonsFactory: ButtonsFactory
    ): AttributesFilter {
        return AttributesFilter(buttonsFactory = buttonsFactory)
    }
}