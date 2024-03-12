package com.trionesdev.phecda.gateway.tcp.process

import com.trionesdev.phecda.gateway.core.GatewayProcess
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import io.netty.buffer.ByteBuf
import io.netty.buffer.EmptyByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandler.Sharable
import io.netty.channel.ChannelInboundHandlerAdapter

@Sharable
abstract class TcpGatewayProcess : ChannelInboundHandlerAdapter(), GatewayProcess {
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

    fun handlers():MutableList<ChannelHandler>{
        return mutableListOf()
    }

    abstract fun match(data: ByteArray?): Boolean

    abstract fun authentication(data: ByteArray?): Boolean

    abstract fun process(data: ByteArray): ByteArray?

    override fun sendCommand(command: PhecdaCommand) {

    }
}