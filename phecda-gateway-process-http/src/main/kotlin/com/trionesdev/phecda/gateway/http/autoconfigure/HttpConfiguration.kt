package com.trionesdev.phecda.gateway.http.autoconfigure

import com.trionesdev.phecda.gateway.http.process.HttpGatewayProcess
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
open class HttpConfiguration(
    private val httpGatewayProcesses: MutableList<HttpGatewayProcess>
) {
    @Bean
    open fun routes(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.all()
        ) { request: ServerRequest? ->
            httpGatewayProcesses.find { it.requestMatch(request) }?.let { process ->
                return@route process.doProcess(request)?.let {
                    ServerResponse.ok().bodyValue(it)
                } ?: let {
                    ServerResponse.ok().build()
                }
            }
            ServerResponse.ok().build()
        }
    }
}