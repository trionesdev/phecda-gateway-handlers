package com.trionesdev.phecda.gateway.mqtt.autoconfigure

import com.trionesdev.phecda.gateway.mqtt.handle.MqttGatewayHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.springframework.stereotype.Component

@Component
class GatewayMqttCallback(
    private var mqttClient: IMqttAsyncClient,
    private val mqttGatewayProcesses: MutableList<MqttGatewayHandler>
) : MqttCallbackExtended {
    override fun connectionLost(cause: Throwable?) {

    }


    override fun messageArrived(topic: String?, message: MqttMessage?) {

    }

    override fun deliveryComplete(token: IMqttDeliveryToken?) {

    }

    override fun connectComplete(reconnect: Boolean, serverURI: String?) {
        if (mqttGatewayProcesses.isEmpty()) {
            return
        }
        GlobalScope.launch {
            for (process in mqttGatewayProcesses) {
                process.connectComplete(reconnect, mqttClient)
            }
        }
    }

}