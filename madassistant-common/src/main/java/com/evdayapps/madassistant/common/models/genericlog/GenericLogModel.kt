package com.evdayapps.madassistant.common.models.genericlog

import org.json.JSONObject

data class GenericLogModel(
    val threadName: String,
    val type: Int,
    val tag: String,
    val message: String,
    val data: JSONObject?
) {

    companion object {
        private const val KEY_threadName = "threadName"
        private const val KEY_type = "type"
        private const val KEY_tag = "tag"
        private const val KEY_message = "message"
        private const val KEY_data = "data"
    }

    constructor(
        threadName: String,
        type: Int,
        tag: String,
        message: String,
        data: Map<String, Any?>? = null
    ) : this(
        threadName = threadName,
        tag = tag,
        type = type,
        message = message,
        data = JSONObject().apply {
            data?.forEach {
                put(it.key, it.value)
            }
        }
    )

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        threadName = json.getString(KEY_threadName),
        type = json.getInt(KEY_type),
        tag = json.getString(KEY_tag),
        message = json.getString(KEY_message),
        data = json.optJSONObject(KEY_data)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_threadName, threadName)
            put(KEY_type, type)
            put(KEY_tag, tag)
            put(KEY_message, message)
            putOpt(KEY_data, data)
        }
    }

}