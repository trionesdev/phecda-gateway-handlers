package com.trionesdev.phecda.gateway.tcp.process

import com.trionesdev.phecda.gateway.core.GatewayProcess
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand

abstract class TcpGatewayProcess : GatewayProcess {

    abstract fun match(data: ByteArray)

    abstract fun authentication(data: ByteArray)

    override fun sendCommand(command: PhecdaCommand) {

    }
}