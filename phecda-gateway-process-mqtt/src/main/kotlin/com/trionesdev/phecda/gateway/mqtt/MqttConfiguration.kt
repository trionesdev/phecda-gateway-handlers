package com.trionesdev.phecda.gateway.mqtt

import jakarta.annotation.PostConstruct
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.springframework.context.annotation.Configuration

@Configuration
open class MqttConfiguration(
    private val mqttClient: IMqttAsyncClient,
    private val mqttCallback: GatewayMqttCallback
) {

    @PostConstruct
    fun init() {
        mqttConnect()
    }

    private fun mqttConnect() {
        mqttClient.setCallback(mqttCallback)
        mqttClient.connect()
    }
}