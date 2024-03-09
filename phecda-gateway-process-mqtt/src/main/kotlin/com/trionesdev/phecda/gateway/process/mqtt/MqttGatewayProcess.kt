package com.trionesdev.phecda.gateway.process.mqtt

import com.trionesdev.phecda.gateway.process.core.GatewayProcess
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class MqttGatewayProcess : GatewayProcess {
    abstract fun requestMatch(topic: String,message: MqttMessage):Boolean
}