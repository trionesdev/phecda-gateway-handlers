package com.trionesdev.phecda.gateway.core

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import com.trionesdev.phecda.gateway.core.model.PhecdaEvent

interface GatewayProcess {
    companion object{
       const val PROPERTIES_POST_TOPIC= "phecda-properties-post"
       const val EVENTS_POST_TOPIC= "phecda-events-post"
    }
    fun postProperties(properties: PhecdaEvent)
    fun postEvents(properties: PhecdaEvent)
    fun sendCommand(command: PhecdaCommand)
}