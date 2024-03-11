package com.trionesdev.phecda.gateway.mqtt.process

import org.springframework.stereotype.Component

@Component
class MqttGatewayProcessFactory(private val mqttGatewayProcessList: MutableList<MqttGatewayProcess>) {
    private val processMap: MutableMap<String, MqttGatewayProcess> = mutableMapOf()


}