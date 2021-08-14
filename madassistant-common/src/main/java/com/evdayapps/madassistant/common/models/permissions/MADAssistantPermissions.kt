package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getOr
import org.json.JSONObject

data class MADAssistantPermissions(
    var timestampStart: Long? = null,
    var timestampEnd: Long? = null,
    var deviceId: String? = null,
    var networkCalls: ApiCallsPermissionModel = ApiCallsPermissionModel(),
    var analytics: AnalyticsPermissionModel = AnalyticsPermissionModel(),
    var exceptions: ExceptionsPermissionModel = ExceptionsPermissionModel(),
    var genericLogs: GenericLogsPermissionModel = GenericLogsPermissionModel(),
    var randomString: String? = System.currentTimeMillis().toString()
) {

    companion object {
        private const val KEY_randomString = "randomString"
        private const val KEY_timestampStart = "timestampStart"
        private const val KEY_timestampEnd = "timestampEnd"
        private const val KEY_deviceId = "deviceId"
        private const val KEY_networkCalls = "networkCalls"
        private const val KEY_genericLogs = "genericLogs"
        private const val KEY_analytics = "analytics"
        private const val KEY_exceptions = "exceptions"
    }

    constructor(json: JSONObject) : this() {
        randomString = json.optString(KEY_randomString, "")

        timestampStart = json.getOr(KEY_timestampStart, null)
        timestampEnd = json.getOr(KEY_timestampEnd, null)

        deviceId = json.optString(KEY_deviceId, "")

        networkCalls = json.getJSONObject(KEY_networkCalls).run {
            try {
                ApiCallsPermissionModel(this)
            } catch (ex: Exception) {
                ApiCallsPermissionModel()
            }
        }

        analytics = json.getJSONObject(KEY_analytics).run {
            try {
                AnalyticsPermissionModel(this)
            } catch (ex: Exception) {
                AnalyticsPermissionModel()
            }
        }

        exceptions = json.getJSONObject(KEY_exceptions).run {
            try {
                ExceptionsPermissionModel(this)
            } catch (ex: Exception) {
                ExceptionsPermissionModel()
            }
        }

        genericLogs = json.getJSONObject(KEY_genericLogs).run {
            try {
                GenericLogsPermissionModel(this)
            } catch (ex: Exception) {
                GenericLogsPermissionModel()
            }
        }
    }

    fun toJsonObject(): JSONObject {
        networkCalls.apply { enabled = share || read }
        genericLogs.apply { enabled = share || read }
        analytics.apply { enabled = share || read }
        exceptions.apply { enabled = share || read }

        return JSONObject().apply {
            put(KEY_deviceId, deviceId)

            put(KEY_randomString, randomString)

            putOpt(KEY_timestampStart, timestampStart)
            putOpt(KEY_timestampEnd, timestampEnd)

            putOpt(KEY_networkCalls, networkCalls.toJsonObject())
            putOpt(KEY_genericLogs, genericLogs.toJsonObject())
            putOpt(KEY_exceptions, exceptions.toJsonObject())
            putOpt(KEY_analytics, analytics.toJsonObject())
        }
    }
}