package com.trionesdev.phecda.gateway.tcp.autoconfigure

import com.trionesdev.phecda.gateway.tcp.process.TcpGatewayProcessor
import io.netty.buffer.Unpooled
import io.netty.util.AttributeKey
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono
import reactor.netty.Connection
import reactor.netty.tcp.TcpServer
import java.util.concurrent.ConcurrentHashMap
import javax.annotation.PostConstruct


@Configuration
@EnableConfigurationProperties(TcpProperties::class)
open class TcpConfiguration(
    private val tcpProperties: TcpProperties,
    private val tcpGatewayProcessList: MutableList<TcpGatewayProcessor>
) {
    companion object {
        const val AUTHENTICATED: String = "authenticated"
        val connectionMap = ConcurrentHashMap<String, Connection>()
    }

    private var logger: Logger = LoggerFactory.getLogger(TcpConfiguration::class.java)


    @PostConstruct
    fun init() {
        logger.info("TcpConfiguration init")
        createTcpServer()
    }

    private fun createTcpServer() {
        val server = TcpServer.create()
            .wiretap(true)
            .doOnConnection { conn ->
                conn.inbound()
                    .receive()
                    .asByteArray()
                    .doOnError { err ->
                        logger.error("TcpConnection error", err)
                    }.subscribe { msg ->
                        if (conn.channel().attr(AttributeKey.valueOf<Boolean>(AUTHENTICATED))
                                ?.get() != true
                        ) { //未通过认证的连接，进行匹配并认证
                            tcpGatewayProcessList.find { it.match(msg) }?.let { process ->
                                if (!process.authentication(msg)) { //认证不通过，断开连接
                                    conn.dispose()
                                }
                                conn.channel().attr(AttributeKey.valueOf<Boolean>(AUTHENTICATED)).set(true)
                                if (process.handlers().isNotEmpty()) {
                                    process.handlers().forEach {
                                        conn.channel().pipeline().addFirst(it)
                                    }
                                }
                                conn.channel().pipeline()
                                    .addBefore("reactor.right.reactiveBridge", "pehcdaHandler", process)
                            } ?: let {
                                logger.error("无匹配的处理类:{}", msg)
                                conn.dispose()
                            }
                        } else {
                            logger.info("TcpConfiguration receive msg:{}", msg)
                            val resultBuf = Unpooled.copiedBuffer(msg)
                            conn.outbound().send(Mono.just(resultBuf)).then().subscribe()
                        }
                    }
            }
            .host(tcpProperties.host)
            .port(tcpProperties.port)
            .bindNow()
        server.onDispose().subscribe {
            logger.info("TcpConfiguration server dispose")
        }
        logger.info("Netty TcpServer started on port {}", tcpProperties.port)
    }

}