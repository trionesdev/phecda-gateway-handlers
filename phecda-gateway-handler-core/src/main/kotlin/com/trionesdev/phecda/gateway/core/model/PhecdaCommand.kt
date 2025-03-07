package com.trionesdev.phecda.gateway.core.model

/**
 * 指令对象
 */
class PhecdaCommand {
    var version: String? = null
    var id: String? = null
    var method: String? = null
    var productKey: String? = null
    var deviceName: String? = null
    var commandName: String? = null
    var params: MutableMap<String, String?>? = null
    var body: MutableMap<String, Any?>? = null
}