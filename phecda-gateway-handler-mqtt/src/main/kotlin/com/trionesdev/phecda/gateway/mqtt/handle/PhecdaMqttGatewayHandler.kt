package com.trionesdev.phecda.gateway.mqtt.handle

import com.alibaba.fastjson2.JSON
import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.eclipse.paho.client.mqttv3.MqttMessage

abstract class PhecdaMqttGatewayHandler : MqttGatewayHandler() {
    override fun onConnectComplete(reconnect: Boolean) {
        TODO("Not yet implemented")
    }

    override fun sendCommand(command: PhecdaCommand) {
        mqttClient.publish(
            "phecda/gateway/${command.productKey}/${command.deviceName}}/thing/command",
            MqttMessage(JSON.toJSONBytes(command))
        )
    }


}