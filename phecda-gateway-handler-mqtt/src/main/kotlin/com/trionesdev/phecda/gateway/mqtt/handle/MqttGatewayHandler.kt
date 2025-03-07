package com.trionesdev.phecda.gateway.mqtt.handle

import com.trionesdev.phecda.gateway.core.AbsGatewayHandler
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.IMqttMessageListener
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class MqttGatewayHandler : AbsGatewayHandler() {
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