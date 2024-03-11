package com.trionesdev.phecda.gateway.tcp.autoconfigure

import com.trionesdev.phecda.gateway.tcp.process.TcpGatewayProcess
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.logging.LoggingHandler
import io.netty.handler.timeout.ReadTimeoutHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import reactor.netty.tcp.TcpServer
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import reactor.core.publisher.Mono


@Configuration
open class TcpConfiguration(private val tcpGatewayProcessList: MutableList<TcpGatewayProcess>) {
    private var logger: Logger = LoggerFactory.getLogger(TcpConfiguration::class.java)
    @PostConstruct
    fun init() {
        logger.info("TcpConfiguration init")
        createTcpServer()
    }

    private fun createTcpServer() {
        val server = TcpServer.create()
            .wiretap(true)
            .doOnBind { channel ->
                channel.channelInitializer()
            }
            .host("localhost").port(1883).bindNow();
        server.onDispose().block();
    }

}