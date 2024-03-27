package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcessor
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient

abstract class MqttGatewayProcessor : AbsGatewayProcessor() {
    abstract fun connectComplete(reconnect: Boolean, mqttClient: IMqttAsyncClient)
}