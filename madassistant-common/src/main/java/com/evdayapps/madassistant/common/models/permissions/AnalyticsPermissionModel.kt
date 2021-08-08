package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONObject

data class AnalyticsPermissionModel(
    var enabled: Boolean = false,
    var read: Boolean = false,
    var share: Boolean = false,
    var filterDestination: String? = null,
    var filterEventName: String? = null,
    var filterParamData: String? = null,
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_read = "read"
        private const val KEY_share = "share"

        private const val KEY_filterDestination = "filterDestination"
        private const val KEY_filterEventName = "filterEventName"
        private const val KEY_filterParamData = "filterParamData"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        read = json.optBoolean(KEY_read, false),
        share = json.optBoolean(KEY_share, false),
        filterDestination = json.getStringOrNull(KEY_filterDestination),
        filterEventName = json.getStringOrNull(KEY_filterEventName),
        filterParamData = json.getStringOrNull(KEY_filterParamData),
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            put(KEY_read, read)
            put(KEY_share, share)

            putOpt(KEY_filterDestination, filterDestination)
            putOpt(KEY_filterEventName, filterEventName)
            putOpt(KEY_filterParamData, filterParamData)
        }
    }

}
