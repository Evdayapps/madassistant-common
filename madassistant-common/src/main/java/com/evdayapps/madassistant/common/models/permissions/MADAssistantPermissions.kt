package com.evdayapps.madassistant.common.models.permissions

import com.evdayapps.madassistant.common.kotlinx.getOr
import org.json.JSONObject

/**
 * Main permission class
 * @property deviceId The installation identifier of the client (optional if no device id check in sdk)
 * @property timestampStart Optional. Time from which data can be logged.
 * @property timestampEnd Optional. Time till which data can be logged. Should be after [timestampStart] if its been specified.
 * @property encrypted Should the logs (and app configurations) be encrypted?
 * @property networkCalls Permissions for network/api call logs
 * @property analytics Permissions for analytics logs
 * @property exceptions Permissions for exceptions logs
 * @property genericLogs Permissions for generic logs
 * @property randomString A random string (current timestamp) added to
 */
data class MADAssistantPermissions constructor(
    val deviceId: String?,
    val timestampStart: Long?,
    val timestampEnd: Long?,
    val encrypted: Boolean,
    val networkCalls: ApiCallsPermissionModel,
    val analytics: AnalyticsPermissionModel,
    val exceptions: ExceptionsPermissionModel,
    val genericLogs: GenericLogsPermissionModel,
    val randomString: String? = System.currentTimeMillis().toString()
) {

    companion object {
        private const val KEY_randomString = "randomString"
        private const val KEY_timestampStart = "timestampStart"
        private const val KEY_timestampEnd = "timestampEnd"
        private const val KEY_encrypted = "encrypted"
        private const val KEY_deviceId = "deviceId"
        private const val KEY_networkCalls = "networkCalls"
        private const val KEY_genericLogs = "genericLogs"
        private const val KEY_analytics = "analytics"
        private const val KEY_exceptions = "exceptions"
    }

    constructor(json: JSONObject) : this(
        randomString = json.optString(KEY_randomString, ""),
        timestampStart = json.getOr(KEY_timestampStart, null),
        timestampEnd = json.getOr(KEY_timestampEnd, null),
        deviceId = json.optString(KEY_deviceId, ""),
        encrypted = json.getOr(KEY_encrypted, false),
        networkCalls = json.optJSONObject(KEY_networkCalls)?.run {
            try {
                ApiCallsPermissionModel(this)
            } catch (ex: Exception) {
                ApiCallsPermissionModel()
            }
        } ?: ApiCallsPermissionModel(),
        analytics = json.optJSONObject(KEY_analytics)?.run {
            try {
                AnalyticsPermissionModel(this)
            } catch (ex: Exception) {
                AnalyticsPermissionModel()
            }
        } ?: AnalyticsPermissionModel(),
        exceptions = json.optJSONObject(KEY_exceptions)?.run {
            try {
                ExceptionsPermissionModel(this)
            } catch (ex: Exception) {
                ExceptionsPermissionModel()
            }
        } ?: ExceptionsPermissionModel(),
        genericLogs = json.optJSONObject(KEY_genericLogs)?.run {
            try {
                GenericLogsPermissionModel(this)
            } catch (ex: Exception) {
                GenericLogsPermissionModel()
            }
        } ?: GenericLogsPermissionModel()
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_deviceId, deviceId)

            put(KEY_randomString, randomString)
            put(KEY_encrypted, encrypted)

            putOpt(KEY_timestampStart, timestampStart)
            putOpt(KEY_timestampEnd, timestampEnd)

            put(KEY_networkCalls, networkCalls.toJsonObject())
            put(KEY_genericLogs, genericLogs.toJsonObject())
            put(KEY_exceptions, exceptions.toJsonObject())
            put(KEY_analytics, analytics.toJsonObject())
        }
    }
}