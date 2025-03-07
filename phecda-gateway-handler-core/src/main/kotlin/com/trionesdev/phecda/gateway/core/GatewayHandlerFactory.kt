package com.trionesdev.phecda.gateway.core


import jakarta.annotation.PostConstruct
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Objects

@Component
class GatewayHandlerFactory(private val gatewayHandlerList: MutableList<GatewayHandler>) {
    private var logger: Logger = LoggerFactory.getLogger(GatewayHandlerFactory::class.java)
    private val handlerMap: MutableMap<String, GatewayHandler> = mutableMapOf()

    @PostConstruct
    fun init() {
        if (gatewayHandlerList.isEmpty()) {
            return
        }
        gatewayHandlerList.forEach { gatewayProcess ->
            AnnotationUtils.getAnnotation(gatewayProcess.javaClass, GatewayHandlerComponent::class.java)
                ?.let { component ->
                    component.productKeys.forEach { productKey ->
                        val productProcess = handlerMap[productKey]
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
                        handlerMap[productKey] = gatewayProcess
                    }
                }
        }
    }

    fun getGatewayHandler(productKey: String): GatewayHandler? {
        return handlerMap[productKey]
    }
}