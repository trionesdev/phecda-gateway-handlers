package com.trionesdev.phecda.gateway.tcp.process

import com.trionesdev.phecda.gateway.core.model.PhecdaCommand
import org.springframework.kafka.core.KafkaTemplate

abstract class PhecdaTcpGatewayProcess : TcpGatewayProcess() {
    override fun match(data: ByteArray?): Boolean {
        return false
    }

    override fun authentication(data: ByteArray?): Boolean {
        return false
    }

    override fun process(data: ByteArray): ByteArray? {
       return null
    }

    override fun commandTransform(command: PhecdaCommand): ByteArray? {
        TODO("Not yet implemented")
    }

}