package com.trionesdev.phecda.gateway.mqtt

import com.trionesdev.phecda.gateway.mqtt.process.MqttGatewayProcess
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.stereotype.Component

@Component
class GatewayMqttCallback(
    private val mqttClient: IMqttAsyncClient,
    private val mqttGatewayProcesses: MutableList<MqttGatewayProcess>
) : MqttCallbackExtended {
    override fun connectionLost(cause: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun messageArrived(topic: String?, message: MqttMessage?) {
        for (process in mqttGatewayProcesses) {
            if (process.requestMatch(topic, message)) {
                process.process(topic, message)
                break
            }
        }
    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {
        TODO("Not yet implemented")
    }

    override fun connectComplete(reconnect: Boolean, serverURI: String?) {

    }

    fun subscribe() {

    }
}