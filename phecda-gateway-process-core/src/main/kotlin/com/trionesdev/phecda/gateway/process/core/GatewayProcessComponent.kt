package com.trionesdev.phecda.gateway.process.core

import org.springframework.stereotype.Component

@Target
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GatewayProcessComponent(
    val productKey: String = ""
)
