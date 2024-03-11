package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.eclipse.paho.client.mqttv3.MqttMessage

open class PhecdaMqttGatewayProcess: MqttGatewayProcess() {
    override fun requestMatch(topic: String?, message: MqttMessage?): Boolean {
        return false
    }

    override fun requestProcess(topic: String?, message: MqttMessage?) {

    }

    override fun sendCommand(command: PhecdaCommand) {

    }


}