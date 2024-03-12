package com.trionesdev.phecda.gateway.core

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent
import org.springframework.kafka.core.KafkaTemplate

abstract class AbsGatewayProcess : GatewayProcess {
    protected var kafkaTemplate: KafkaTemplate<String, ByteArray>? = null
//    constructor()
    constructor(kafkaTemplate: KafkaTemplate<String, ByteArray>) {
        this.kafkaTemplate = kafkaTemplate
    }

    override fun postProperties(properties: PhecdaEvent) {
        kafkaTemplate?.send(GatewayProcess.PROPERTIES_POST_TOPIC, JSON.toJSONBytes(properties))
    }

    override fun postEvents(properties: PhecdaEvent) {
        kafkaTemplate?.send(GatewayProcess.EVENTS_POST_TOPIC, JSON.toJSONBytes(properties))
    }
}