package com.trionesdev.phecda.gateway.core.model

class PhecdaEvent {
    var version: String? = null
    var id: String? = null
    var deviceName: String? = null
    var productKey: String? = null
    var sourceName: String? = null
    var ts: Long? = null
    var readings: MutableMap<String, Reading>? = mutableMapOf()
    var tags: MutableMap<String, Any?>? = mutableMapOf()
    class Reading {
        var identifier: String? = null
        var valueType: String? = null
        var utils: String? = null
        var binaryValue: ByteArray? = null
        var mediaType: String? = null
        var objectValue: Any? = null
        var value: String? = null
        var ts: Long? = null
    }
}