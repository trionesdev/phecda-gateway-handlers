package com.trionesdev.phecda.gateway.mqtt.process

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class PhecdaMqttGatewayProcessor : MqttGatewayProcessor() {
    override fun onConnectComplete(reconnect: Boolean) {
        TODO("Not yet implemented")
    }

    override fun sendCommand(command: PhecdaCommand) {
        mqttClient.publish(
            "pheda/gateway/${command.productKey}/${command.deviceName}}/thing/service",
            MqttMessage(JSON.toJSONBytes(command))
        )
    }


}