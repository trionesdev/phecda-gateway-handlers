package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class PhecdaHttpGatewayProcess(kafkaTemplate: KafkaTemplate<String, ByteArray>) : HttpGatewayProcess(kafkaTemplate) {
    override fun requestMatch(request: ServerRequest?): Boolean {
        return false
    }

    override fun requestProcess(request: ServerRequest?): Mono<ServerResponse> {
        return ServerResponse.ok().build()
    }

    override fun sendCommand(command: PhecdaCommand) {

    }


}