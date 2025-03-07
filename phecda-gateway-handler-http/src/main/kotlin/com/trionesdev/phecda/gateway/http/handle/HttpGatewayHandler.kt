package com.trionesdev.phecda.gateway.http.handle

import com.trionesdev.phecda.gateway.core.AbsGatewayHandler
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import reactor.netty.http.server.HttpServerRequest

abstract class HttpGatewayHandler : AbsGatewayHandler() {

    override fun sendCommand(command: PhecdaCommand) {

    }

    abstract fun match(request: HttpServerRequest?): Boolean
    abstract fun doProcess(request: HttpServerRequest?): Any?

}