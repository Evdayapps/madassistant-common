package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONObject

data class ExceptionsPermissionModel(
    var enabled: Boolean = false,
    var read: Boolean = false,
    var share: Boolean = false,
    // Filters
    var filterType: String? = null,
    var filterMessage: String? = null,
    // Crashes
    var crashesEnabled: Boolean = false,
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_read = "read"
        private const val KEY_share = "share"
        private const val KEY_filterType = "filterType"
        private const val KEY_filterMessage = "filterMessage"
        private const val KEY_logCrashes = "logCrashes"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        read = json.optBoolean(KEY_read, false),
        share = json.optBoolean(KEY_share, false),
        crashesEnabled = json.optBoolean(KEY_logCrashes, false),
        filterType = json.getStringOrNull(KEY_filterType),
        filterMessage = json.getStringOrNull(KEY_filterMessage)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            put(KEY_read, read)
            put(KEY_share, share)

            putOpt(KEY_filterType, filterType)
            putOpt(KEY_filterMessage, filterMessage)
        }
    }

}