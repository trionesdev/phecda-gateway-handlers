package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.AbsGatewayProcessor
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class MqttGatewayProcessor : AbsGatewayProcessor() {
    protected lateinit var mqttClient: IMqttAsyncClient
    fun connectComplete(reconnect: Boolean, mqttClient: IMqttAsyncClient) {
        this.mqttClient = mqttClient
        onConnectComplete(reconnect)
    }

    abstract fun onConnectComplete(reconnect: Boolean)
    fun publish(topic: String, message: MqttMessage) {
        mqttClient.publish(topic, message)
    }

    fun subscribe(topic: String, qos: Int, listener: IMqttMessageListener) {
        mqttClient.subscribe(topic, qos, listener)
    }


}