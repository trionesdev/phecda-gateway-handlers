package com.trionesdev.phecda.gateway.http.autoconfigure

import com.fasterxml.jackson.databind.ObjectMapper
import com.trionesdev.phecda.gateway.http.process.HttpGatewayProcessor
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.HttpResponseStatus
import jakarta.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono
import reactor.netty.http.server.HttpServer

@Configuration
@EnableConfigurationProperties(HttpProperties::class)
open class HttpConfiguration(
    private val objectMapper: ObjectMapper,
    private val httpProperties: HttpProperties,
    private val httpGatewayProcesses: MutableList<HttpGatewayProcessor>
) {
    private var logger: Logger = LoggerFactory.getLogger(HttpConfiguration::class.java)


    @PostConstruct
    fun init() {
        createHttpServer()
    }

    private fun createHttpServer() {
        val httpServer = HttpServer.create()
            .handle { request, response ->
                try {
                    val gatewayProcess = httpGatewayProcesses.find { it.match(request) }
                    gatewayProcess?.let {
                        gatewayProcess.doProcess(request)?.let {
                            val res: ByteArray = when (it) {
                                is String -> it.toByteArray()
                                else -> objectMapper.writeValueAsBytes(it)
                            }
                            val resultBuf = Unpooled.copiedBuffer(res)
                            response.send(Mono.just(resultBuf))
                        } ?: let {
                            response.send()
                        }
                    }
                } catch (e: Exception) {
                    logger.error("HttpConfiguration error", e)
                    response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR)
                    response.send()
                }
            }
            .host(httpProperties.host).port(httpProperties.port).bindNow()
        httpServer.onDispose().subscribe {
            logger.info("HttpConfiguration server dispose")
        }
        logger.info("Netty TcpServer started on port {}", httpProperties.port)
    }

}