package com.trionesdev.phecda.gateway.core

import org.springframework.stereotype.Component

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GatewayHandlerComponent(
    val productKeys: Array<String> = []
)
