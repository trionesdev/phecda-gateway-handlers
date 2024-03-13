package com.trionesdev.phecda.gateway.mqtt.process

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class PhecdaMqttGatewayProcess : MqttGatewayProcess() {
    private var mqttClient: IMqttAsyncClient? = null
    abstract fun process(topic: String, message: MqttMessage)

    override fun connectComplete(reconnect: Boolean, mqttClient: IMqttAsyncClient) {
        this.mqttClient = mqttClient
        mqttClient.subscribe("phecda/gateway/+/+/post", 1) { topic, message ->
            process(topic, message)
        }
    }

    override fun sendCommand(command: PhecdaCommand) {
        mqttClient?.publish(
            "pheda/gateway/${command.productKey}/${command.deviceName}}/thing/service",
            MqttMessage(JSON.toJSONBytes(command))
        )
    }


}