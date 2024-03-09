package com.trionesdev.phecda.gateway.core

import jakarta.annotation.PostConstruct
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component

@Component
class GatewayProcessFactory(private val gatewayProcessList: MutableList<GatewayProcess>) {
    private val processMap: MutableMap<String, GatewayProcess> = mutableMapOf()

    @PostConstruct
    fun init() {
        gatewayProcessList.forEach { gatewayProcess ->
            AnnotationUtils.getAnnotation(gatewayProcess.javaClass, GatewayProcessComponent::class.java)
                ?.let { component ->
                    processMap[component.productKey] = gatewayProcess
                }
        }
    }

    fun getGatewayProcess(productKey: String): GatewayProcess? {
        return processMap[productKey]
    }
}