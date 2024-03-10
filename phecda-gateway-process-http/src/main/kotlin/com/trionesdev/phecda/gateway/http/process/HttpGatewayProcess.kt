package com.trionesdev.phecda.gateway.http.process

import com.trionesdev.phecda.gateway.core.GatewayProcess
import org.springframework.web.reactive.function.server.ServerRequest

abstract class HttpGatewayProcess : GatewayProcess {
    abstract fun requestMatch(request: ServerRequest): Boolean
    abstract fun requestProcess(request: ServerRequest)
}