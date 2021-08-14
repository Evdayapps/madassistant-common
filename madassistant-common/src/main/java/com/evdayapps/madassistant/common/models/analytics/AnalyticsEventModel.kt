package com.evdayapps.madassistant.common.models.analytics

import org.json.JSONObject

data class AnalyticsEventModel constructor(
    var threadName: String,
    var destination: String,
    var name: String,
    var parameters: JSONObject
) {

    companion object {
        private const val KEY_threadName = "threadName"
        private const val KEY_destination = "destination"
        private const val KEY_name = "name"
        private const val KEY_parameters = "parameters"
    }

    constructor(
        threadName: String,
        destination: String,
        name: String,
        params: Map<String, Any?>
    ) : this(
        threadName = threadName,
        destination = destination,
        name = name,
        parameters = JSONObject().apply {
            params.forEach {
                put(it.key, it.value)
            }
        }
    )

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        threadName = json.getString(KEY_threadName),
        destination = json.getString(KEY_destination),
        name = json.getString(KEY_name),
        parameters = json.getJSONObject(KEY_parameters)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_threadName, threadName)
            put(KEY_name, name)
            put(KEY_destination, destination)
            put(KEY_parameters, parameters)
        }
    }

}