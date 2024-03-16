package com.trionesdev.phecda.gateway.core

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "phecda.gateway")
class GatewayProcessProperties {
    companion object{
        const val PROPERTIES_POST_TOPIC= "phecda-properties-post"
        const val EVENTS_POST_TOPIC= "phecda-events-post"
    }

    var propertiesPostTopic: String? = PROPERTIES_POST_TOPIC
    var eventsPostTopic: String? = EVENTS_POST_TOPIC
}