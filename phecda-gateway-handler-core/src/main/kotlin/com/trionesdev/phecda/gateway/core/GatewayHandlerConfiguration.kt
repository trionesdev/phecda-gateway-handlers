package com.trionesdev.phecda.gateway.core

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GatewayHandlerProperties::class)
open class GatewayHandlerConfiguration {
}