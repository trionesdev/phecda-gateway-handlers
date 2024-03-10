package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.GatewayProcess
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class MqttGatewayProcess : GatewayProcess {
    abstract fun requestMatch(topic: String?, message: MqttMessage?): Boolean

    abstract fun requestProcess(topic: String?, message: MqttMessage?)
}