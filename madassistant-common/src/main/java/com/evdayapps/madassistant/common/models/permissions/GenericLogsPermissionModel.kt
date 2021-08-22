package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONObject

data class GenericLogsPermissionModel(
    val enabled: Boolean = false,
    val filterTag: String? = null,
    val filterMessage: String? = null,
    val logDebug: Boolean = false,
    val logInfo: Boolean = false,
    val logWarning: Boolean = false,
    val logError: Boolean = false,
    val logVerbose: Boolean = false
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_filterTag = "filterTag"
        private const val KEY_filterMessage = "filterMessage"
        private const val KEY_logVerbose = "logVerbose"
        private const val KEY_logWarning = "logWarning"
        private const val KEY_logInfo = "logInfo"
        private const val KEY_logError = "logError"
        private const val KEY_logDebug = "logDebug"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        filterTag = json.getStringOrNull(KEY_filterTag),
        filterMessage = json.getStringOrNull(KEY_filterMessage),
        logVerbose = json.optBoolean(KEY_logVerbose, false),
        logInfo = json.optBoolean(KEY_logInfo, false),
        logWarning = json.optBoolean(KEY_logWarning, false),
        logError = json.optBoolean(KEY_logError, false),
        logDebug = json.optBoolean(KEY_logDebug, false)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)

            put(KEY_logVerbose, logVerbose)
            put(KEY_logInfo, logInfo)
            put(KEY_logWarning, logWarning)
            put(KEY_logError, logError)
            put(KEY_logDebug, logDebug)

            putOpt(KEY_filterTag, filterTag)
            putOpt(KEY_filterMessage, filterMessage)
        }
    }

}