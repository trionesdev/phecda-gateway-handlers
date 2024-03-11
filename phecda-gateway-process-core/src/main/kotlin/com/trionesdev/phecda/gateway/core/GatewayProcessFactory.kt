package com.trionesdev.phecda.gateway.core


import org.springframework.core.annotation.AnnotationUtils
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.Objects

@Component
class GatewayProcessFactory(private val gatewayProcessList: MutableList<GatewayProcess>) {
    private var logger: Logger = LoggerFactory.getLogger(GatewayProcessFactory::class.java)
    private val processMap: MutableMap<String, GatewayProcess> = mutableMapOf()

    @PostConstruct
    fun init() {
        if (gatewayProcessList.isEmpty()) {
            return
        }
        gatewayProcessList.forEach { gatewayProcess ->
            AnnotationUtils.getAnnotation(gatewayProcess.javaClass, GatewayProcessComponent::class.java)
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

    fun getGatewayProcess(productKey: String): GatewayProcess? {
        return processMap[productKey]
    }
}