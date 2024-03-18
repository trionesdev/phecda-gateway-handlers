package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import reactor.netty.http.server.HttpServerRequest

class PhecdaHttpGatewayProcess : HttpGatewayProcess() {
    override fun match(request: HttpServerRequest?): Boolean {
        return false
    }

    override fun doProcess(request: HttpServerRequest?): Any? {
        return null
    }

    override fun sendCommand(command: PhecdaCommand) {

    }


}