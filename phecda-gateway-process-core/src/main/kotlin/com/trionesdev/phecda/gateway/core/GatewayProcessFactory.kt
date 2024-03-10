package com.trionesdev.phecda.gateway.core


import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class GatewayProcessFactory(private val gatewayProcessList: MutableList<GatewayProcess>) {
    private val processMap: MutableMap<String, GatewayProcess> = mutableMapOf()

    @PostConstruct
    fun init() {
        if (gatewayProcessList.isEmpty()) {
            return
        }
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