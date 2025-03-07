package com.trionesdev.phecda.gateway.udp.handle

import com.trionesdev.phecda.gateway.core.AbsGatewayHandler
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent


abstract class UdpGatewayHandler: AbsGatewayHandler() {
    override fun postProperties(properties: PhecdaEvent) {
        TODO("Not yet implemented")
    }

    override fun postEvents(properties: PhecdaEvent) {
        TODO("Not yet implemented")
    }

    override fun sendCommand(command: PhecdaCommand) {
        TODO("Not yet implemented")
    }
}