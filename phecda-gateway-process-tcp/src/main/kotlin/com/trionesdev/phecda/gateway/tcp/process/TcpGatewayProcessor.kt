package com.trionesdev.phecda.gateway.tcp.process

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.GatewayProcessor
import com.trionesdev.phecda.gateway.core.GatewayProcessProperties
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent
import com.trionesdev.phecda.gateway.tcp.autoconfigure.TcpConfiguration.Companion.connectionMap
import io.netty.buffer.ByteBuf
import io.netty.buffer.EmptyByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelInboundHandlerAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import reactor.core.publisher.Mono
import reactor.netty.Connection

@Sharable
abstract class TcpGatewayProcessor : ChannelInboundHandlerAdapter(), GatewayProcessor {
    @Autowired
    protected var gatewayProcessProperties: GatewayProcessProperties? = null

    @Autowired
    protected var kafkaTemplate: KafkaTemplate<String, ByteArray>? = null


    override fun postProperties(properties: PhecdaEvent) {
        kafkaTemplate?.send(
            gatewayProcessProperties?.propertiesPostTopic ?: GatewayProcessProperties.PROPERTIES_POST_TOPIC,
            JSON.toJSONBytes(properties)
        )
    }

    override fun postEvents(properties: PhecdaEvent) {
        kafkaTemplate?.send(gatewayProcessProperties?.eventsPostTopic ?: GatewayProcessProperties.EVENTS_POST_TOPIC, JSON.toJSONBytes(properties))
    }

    fun getConnection(key: String): Connection? {
        return connectionMap[key]
    }

    fun putConnection(key: String, channel: Connection) {
        connectionMap[key] = channel
    }

    override fun channelRead(ctx: io.netty.channel.ChannelHandlerContext?, msg: Any?) {
        if (msg == null || msg === Unpooled.EMPTY_BUFFER || msg is EmptyByteBuf) {
            return
        }
        try {
            if (msg is ByteBuf) {
                val msgBytes = ByteArray(msg.readableBytes())
                msg.readBytes(msgBytes)
                val result = process(msgBytes)
                if (result != null) {
                    val resultBuf = Unpooled.copiedBuffer(result)
                    ctx?.fireChannelRead(resultBuf)
                }
            }
        } catch (err: Throwable) {
            exceptionCaught(ctx, err)
        }
    }

    fun handlers(): MutableList<ChannelHandler> {
        return mutableListOf()
    }

    abstract fun match(data: ByteArray?): Boolean

    abstract fun authentication(data: ByteArray?): Boolean

    abstract fun process(data: ByteArray): ByteArray?

    abstract fun commandTransform(command: PhecdaCommand): ByteArray?

    override fun sendCommand(command: PhecdaCommand) {
        command.deviceName?.let { deviceName ->
            connectionMap[deviceName]?.let { ch ->
                commandTransform(command)?.let { cmdArr ->
                    ch.outbound().sendByteArray(Mono.just(cmdArr)).then().subscribe()
                }
            }
        }
    }
}