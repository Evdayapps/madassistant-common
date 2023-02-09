package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONObject

/**
 * Class for recording Analytics calls
 */
data class AnalyticsPermissionModel(
    val enabled: Boolean = false,
    val filterDestination: String? = null,
    val filterEventName: String? = null,
    val filterParamData: String? = null,
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_filterDestination = "filterDestination"
        private const val KEY_filterEventName = "filterEventName"
        private const val KEY_filterParamData = "filterParamData"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        filterDestination = json.getStringOrNull(KEY_filterDestination),
        filterEventName = json.getStringOrNull(KEY_filterEventName),
        filterParamData = json.getStringOrNull(KEY_filterParamData),
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            putOpt(KEY_filterDestination, filterDestination)
            putOpt(KEY_filterEventName, filterEventName)
            putOpt(KEY_filterParamData, filterParamData)
        }
    }

}
