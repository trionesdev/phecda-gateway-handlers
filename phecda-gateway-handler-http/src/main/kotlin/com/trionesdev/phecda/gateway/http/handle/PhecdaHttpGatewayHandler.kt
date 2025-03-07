package com.trionesdev.phecda.gateway.http.handle

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import reactor.netty.http.server.HttpServerRequest

class PhecdaHttpGatewayHandler : HttpGatewayHandler() {
    override fun match(request: HttpServerRequest?): Boolean {
        return false
    }

    override fun doProcess(request: HttpServerRequest?): Any? {
        return null
    }

    override fun sendCommand(command: PhecdaCommand) {

    }


}