package com.trionesdev.phecda.gateway.http.autoconfigure

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.*

@Configuration
open class HttpConfiguration {
    @Bean
    open fun routes(): RouterFunction<ServerResponse> {
        return RouterFunctions.route(
            RequestPredicates.all()
        ) { request: ServerRequest? ->
            System.out.println(request?.path())
            ServerResponse.ok().build()
        }
    }
}