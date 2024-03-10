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
            for (process: HttpGatewayProcess in httpGatewayProcesses) {
                if (process.requestMatch(request)) {
                    return@route process.requestProcess(request)
                }
            }
            ServerResponse.ok().build()
        }
    }
}