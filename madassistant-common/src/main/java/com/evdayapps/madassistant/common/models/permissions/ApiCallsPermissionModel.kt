package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONException
import org.json.JSONObject

data class ApiCallsPermissionModel(
    val enabled: Boolean = false,
    val filterMethod: String? = null,
    val filterUrl: String? = null
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_filterMethod = "filterMethod"
        private const val KEY_filterUrl = "filterUrl"
    }

    @Throws(JSONException::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        filterMethod = json.getStringOrNull(KEY_filterMethod),
        filterUrl = json.getStringOrNull(KEY_filterUrl)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            putOpt(KEY_filterMethod, filterMethod)
            putOpt(KEY_filterUrl, filterUrl)
        }
    }
}