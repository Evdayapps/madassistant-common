package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONException
import org.json.JSONObject

data class ApiCallsPermissionModel(
    var enabled: Boolean = false,
    var read: Boolean = false,
    var share: Boolean = false,
    var filterMethod: String? = null,
    var filterUrl: String? = null
) {

    companion object {
        private const val KEY_enabled = "enabled"
        private const val KEY_read = "read"
        private const val KEY_share = "share"
        private const val KEY_filterMethod = "filterMethod"
        private const val KEY_filterUrl = "filterUrl"
    }

    @Throws(JSONException::class)
    constructor(json: JSONObject) : this(
        enabled = json.optBoolean(KEY_enabled, false),
        read = json.optBoolean(KEY_read, false),
        share = json.optBoolean(KEY_share, false),
        filterMethod = json.getStringOrNull(KEY_filterMethod),
        filterUrl = json.getStringOrNull(KEY_filterUrl)
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_enabled, enabled)
            put(KEY_read, read)
            put(KEY_share, share)
            putOpt(KEY_filterMethod, filterMethod)
            putOpt(KEY_filterUrl, filterUrl)
        }
    }
}