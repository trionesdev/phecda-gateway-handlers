package com.trionesdev.phecda.gateway.tcp.handle

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand

abstract class PhecdaTcpGatewayHandler : TcpGatewayHandler() {
    override fun match(data: ByteArray?): Boolean {
        return false
    }

    override fun authentication(data: ByteArray?): Boolean {
        return false
    }

    override fun process(data: ByteArray): ByteArray? {
       return null
    }

    override fun commandTransform(command: PhecdaCommand): ByteArray? {
        TODO("Not yet implemented")
    }

}