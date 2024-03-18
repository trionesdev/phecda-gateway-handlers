package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcess
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import reactor.netty.http.server.HttpServerRequest

abstract class HttpGatewayProcess : AbsGatewayProcess() {

    override fun sendCommand(command: PhecdaCommand) {

    }

    abstract fun match(request: HttpServerRequest?): Boolean
    abstract fun doProcess(request: HttpServerRequest?): Any?

}