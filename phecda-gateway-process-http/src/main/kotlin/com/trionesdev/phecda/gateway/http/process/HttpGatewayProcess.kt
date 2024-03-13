package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcess
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class HttpGatewayProcess : AbsGatewayProcess {
    constructor() : super() {
    }

    constructor(kafkaTemplate: KafkaTemplate<String, ByteArray>) : super(kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate
    }

    override fun sendCommand(command: PhecdaCommand) {

    }

    abstract fun requestMatch(request: ServerRequest?): Boolean
    abstract fun requestProcess(request: ServerRequest?): Mono<ServerResponse>

}