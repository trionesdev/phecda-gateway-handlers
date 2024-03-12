package com.trionesdev.phecda.gateway.tcp.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.tcp")
class TcpProperties {
    var host: String = "localhost"
    var port: Int = 8088
}