package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcess
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

abstract class HttpGatewayProcess : AbsGatewayProcess() {

    override fun sendCommand(command: PhecdaCommand) {

    }

    abstract fun requestMatch(request: ServerRequest?): Boolean
    abstract fun doProcess(request: ServerRequest?): Any?

}