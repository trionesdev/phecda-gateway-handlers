package com.trionesdev.phecda.gateway.core.model

class PhecdaCommand {
    var method: String? = null
    var productKey: String? = null
    var deviceName: String? = null
    var commandName: String? = null
    var params: MutableMap<String, String?>? = null
    var body: MutableMap<String, Any?>? = null
}