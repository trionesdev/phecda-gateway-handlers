package com.trionesdev.phecda.gateway.core

import org.springframework.stereotype.Component

@Target
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class GatewayProcessComponent(
    val productKey: String = ""
)
