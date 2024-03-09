package com.trionesdev.phecda.gateway.process.http

import com.trionesdev.phecda.gateway.process.core.GatewayProcess
import org.springframework.web.reactive.function.server.ServerRequest

abstract class HttpGatewayProcess : GatewayProcess {
    abstract fun requestMatch(request: ServerRequest): Boolean
}