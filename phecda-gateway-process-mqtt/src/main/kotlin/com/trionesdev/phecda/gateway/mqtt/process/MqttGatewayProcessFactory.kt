package com.trionesdev.phecda.gateway.mqtt.process

import com.trionesdev.phecda.gateway.core.GatewayProcessComponent
import jakarta.annotation.PostConstruct
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component

@Component
class MqttGatewayProcessFactory(private val mqttGatewayProcessList: MutableList<MqttGatewayProcess>) {
    private val processMap: MutableMap<String, MqttGatewayProcess> = mutableMapOf()

    @PostConstruct
    fun init() {
        mqttGatewayProcessList.forEach { mqttGatewayProcess ->
            AnnotationUtils.getAnnotation(mqttGatewayProcess.javaClass, GatewayProcessComponent::class.java)
                ?.let { component ->
                    processMap[component.productKey] = mqttGatewayProcess
                }
        }
    }
}