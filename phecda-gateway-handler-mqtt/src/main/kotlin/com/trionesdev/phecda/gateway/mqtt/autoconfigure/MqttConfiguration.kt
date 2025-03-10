package com.trionesdev.phecda.gateway.mqtt.autoconfigure

import jakarta.annotation.PostConstruct
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.springframework.context.annotation.Configuration

@Configuration
open class MqttConfiguration (
    private val mqttConnectOptions: MqttConnectOptions,
    private var mqttClient: IMqttAsyncClient,
    private var mqttCallback: GatewayMqttCallback
) {

    @PostConstruct
    fun init() {
        mqttConnect()
    }

    private fun mqttConnect() {
        mqttClient.setCallback(mqttCallback)
        mqttClient.connect(mqttConnectOptions)
    }
}