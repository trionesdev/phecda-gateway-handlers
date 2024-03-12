package com.trionesdev.phecda.gateway.tcp.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand

class PhecdaTcpGatewayProcess: TcpGatewayProcess() {
    override fun match(data: ByteArray?): Boolean {
        return false
    }

    override fun authentication(data: ByteArray?): Boolean {
        return false
    }

    override fun process(data: ByteArray): ByteArray? {
       return null
    }

    override fun sendCommand(command: PhecdaCommand) {

    }
}