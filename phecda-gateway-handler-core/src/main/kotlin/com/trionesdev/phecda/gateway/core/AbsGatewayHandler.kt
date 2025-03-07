package com.trionesdev.phecda.gateway.core

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.GatewayHandlerProperties.Companion.EVENTS_POST_TOPIC
import com.trionesdev.phecda.gateway.core.GatewayHandlerProperties.Companion.PROPERTIES_POST_TOPIC
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate

abstract class AbsGatewayHandler : GatewayHandler {
    @Autowired
    protected var gatewayHandlerProperties: GatewayHandlerProperties? = null

    @Autowired
    protected var kafkaTemplate: KafkaTemplate<String, ByteArray>? = null

    override fun postProperties(properties: PhecdaEvent) {
        kafkaTemplate?.send(
            gatewayHandlerProperties?.propertiesPostTopic ?: PROPERTIES_POST_TOPIC,
            JSON.toJSONBytes(properties)
        )
    }

    override fun postEvents(properties: PhecdaEvent) {
        kafkaTemplate?.send(gatewayHandlerProperties?.eventsPostTopic ?: EVENTS_POST_TOPIC, JSON.toJSONBytes(properties))
    }
}