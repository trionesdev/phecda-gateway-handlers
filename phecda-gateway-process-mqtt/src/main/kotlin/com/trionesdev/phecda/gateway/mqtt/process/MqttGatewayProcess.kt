package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcess
import com.trionesdev.phecda.gateway.core.GatewayProcess
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.kafka.core.KafkaTemplate

abstract class MqttGatewayProcess : AbsGatewayProcess() {
    abstract fun connectComplete(reconnect: Boolean, mqttClient: IMqttAsyncClient)
}