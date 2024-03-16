package com.trionesdev.phecda.gateway.core

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(GatewayProcessProperties::class)
open class GatewayProcessConfiguration {
}