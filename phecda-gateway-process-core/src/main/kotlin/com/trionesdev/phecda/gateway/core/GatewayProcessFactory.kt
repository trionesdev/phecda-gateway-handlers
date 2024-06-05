package com.trionesdev.phecda.gateway.core


import jakarta.annotation.PostConstruct
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Objects

@Component
class GatewayProcessFactory(private val gatewayProcessorList: MutableList<GatewayProcessor>) {
    private var logger: Logger = LoggerFactory.getLogger(GatewayProcessFactory::class.java)
    private val processMap: MutableMap<String, GatewayProcessor> = mutableMapOf()

    @PostConstruct
    fun init() {
        if (gatewayProcessorList.isEmpty()) {
            return
        }
        gatewayProcessorList.forEach { gatewayProcess ->
            AnnotationUtils.getAnnotation(gatewayProcess.javaClass, GatewayProcessorComponent::class.java)
                ?.let { component ->
                    component.productKeys.forEach { productKey ->
                        val productProcess = processMap[productKey]
                        if (Objects.nonNull(productProcess) && productProcess?.equals(gatewayProcess) == false) {
                            if (logger.isWarnEnabled) {
                                logger.warn(
                                    "GatewayProcessComponent productKey is duplicate: {}, previous: {},current:{}",
                                    productKey,
                                    productProcess.javaClass.name,
                                    gatewayProcess.javaClass.name
                                )
                            }
                        }
                        processMap[productKey] = gatewayProcess
                    }
                }
        }
    }

    fun getGatewayProcess(productKey: String): GatewayProcessor? {
        return processMap[productKey]
    }
}