package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcessor
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import reactor.netty.http.server.HttpServerRequest

abstract class HttpGatewayProcessor : AbsGatewayProcessor() {

    override fun sendCommand(command: PhecdaCommand) {

    }

    abstract fun match(request: HttpServerRequest?): Boolean
    abstract fun doProcess(request: HttpServerRequest?): Any?

}