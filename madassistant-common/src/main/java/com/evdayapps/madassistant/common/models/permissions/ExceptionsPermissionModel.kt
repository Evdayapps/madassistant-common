package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONObject

data class ExceptionsPermissionModel(
    val enabled: Boolean = false,
    val filterType: String? = null,
    val filterMessage: String? = null,
    val logCrashes: Boolean = false,
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_filterType = "filterType"
        private const val KEY_filterMessage = "filterMessage"
        private const val KEY_logCrashes = "logCrashes"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        logCrashes = json.optBoolean(KEY_logCrashes, false),
        filterType = json.getStringOrNull(KEY_filterType),
        filterMessage = json.getStringOrNull(KEY_filterMessage)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            put(KEY_logCrashes, logCrashes)
            putOpt(KEY_filterType, filterType)
            putOpt(KEY_filterMessage, filterMessage)
        }
    }

}