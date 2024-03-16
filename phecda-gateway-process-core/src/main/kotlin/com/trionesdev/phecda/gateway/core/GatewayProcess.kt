package com.trionesdev.phecda.gateway.core

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent

interface GatewayProcess {

    fun postProperties(properties: PhecdaEvent)
    fun postEvents(properties: PhecdaEvent)
    fun sendCommand(command: PhecdaCommand)
}