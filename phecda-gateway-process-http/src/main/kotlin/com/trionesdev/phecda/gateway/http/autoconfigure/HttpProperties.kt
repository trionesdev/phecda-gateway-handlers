package com.trionesdev.phecda.gateway.http.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "phecda.gateway.http")
class HttpProperties {
    var host: String = "localhost"
    var port: Int = 9001
}