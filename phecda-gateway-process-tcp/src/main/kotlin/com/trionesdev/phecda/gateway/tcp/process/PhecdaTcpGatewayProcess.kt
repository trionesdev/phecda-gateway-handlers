package com.trionesdev.phecda.gateway.tcp.process

import com.trionesdev.phecda.gateway.core.model.PhecdaEvent

class PhecdaTcpGatewayProcess: TcpGatewayProcess() {
    override fun match(data: ByteArray?): Boolean {
        TODO("Not yet implemented")
    }

    override fun authentication(data: ByteArray?): Boolean {
        TODO("Not yet implemented")
    }

    override fun process(data: ByteArray): ByteArray? {
        TODO("Not yet implemented")
    }
}