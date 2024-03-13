package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

class PhecdaHttpGatewayProcess : HttpGatewayProcess() {
    override fun requestMatch(request: ServerRequest?): Boolean {
        return false
    }

    override fun doProcess(request: ServerRequest?): Any? {
        return null
    }

    override fun sendCommand(command: PhecdaCommand) {

    }


}