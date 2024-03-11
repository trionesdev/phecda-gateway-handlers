package com.trionesdev.phecda.gateway.core

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand

interface GatewayProcess {
    fun sendCommand(command: PhecdaCommand)
}