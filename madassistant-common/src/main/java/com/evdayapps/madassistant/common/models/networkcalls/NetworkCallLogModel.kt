package com.evdayapps.madassistant.common.models.networkcalls

import com.evdayapps.madassistant.common.models.exceptions.ExceptionModel
import org.json.JSONArray
import org.json.JSONObject

/**
 * // Request
 * val requestHeaders : Map<String></String>, String>,
 * val requestMethod : String,
 * val requestTimestamp : Long,
 * val requestUrl : String,
 * val requestBody: Map<String></String>, *>,
 * // Response
 * val responseHeaders : Map<String></String>, String>,
 * val responseStatusCode: Int,
 * val responseTimestamp : Long,
 * val response : String
 */
data class NetworkCallLogModel(
    var threadName: String? = null,
    var connectTimeoutMillis: Int? = null,
    var readTimeoutMillis: Int? = null,
    var writeTimeoutMillis: Int? = null,
    var protocol: String? = null,
    var method: String = "",
    var url: String = "",
    var requestHeaders: JSONArray? = null,
    var requestTimestamp: Long = 0,
    var requestBody: String? = null,
    var responseHeaders: JSONArray? = null,
    var responseStatusCode: Int? = null,
    var responseTimestamp: Long = 0,
    var gzippedLength: Long? = null,
    var responseLength: Long? = null,
    var responseBody: String? = null,
    var exception: ExceptionModel? = null
) {

    companion object {
        private const val KEY_threadName = "threadName"
        private const val KEY_protocol = "protocol"
        const val KEY_method = "method"
        const val KEY_url = "url"

        // Request
        private const val KEY_connect_timeout_ms = "connect_timeout_ms"
        private const val KEY_read_timeout_ms = "read_timeout_ms"
        private const val KEY_write_timeout_ms = "write_timeout_ms"
        const val KEY_request_timestamp = "request_timestamp"
        const val KEY_request_headers = "request_headers"
        const val KEY_request_body = "request_body"

        // Response
        const val KEY_response_headers = "response_headers"
        const val KEY_response_status_code = "response_status"
        const val KEY_response_timestamp = "response_timestamp"
        const val KEY_response_body = "response_body"
        private const val KEY_response_length = "response_length"
        private const val KEY_response_gzipped_length = "response_gzipped_length"

        // Exception
        private const val KEY_exception = "exception"
    }

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        threadName = json.getString(KEY_threadName),
        protocol = json.optString(KEY_protocol, null),
        method = json.optString(KEY_method, null),
        url = json.optString(KEY_url, null),

        // Request
        connectTimeoutMillis = json.getInt(KEY_connect_timeout_ms),
        readTimeoutMillis = json.getInt(KEY_read_timeout_ms),
        writeTimeoutMillis = json.getInt(KEY_write_timeout_ms),
        requestTimestamp = json.getLong(KEY_request_timestamp),
        requestHeaders = json.getJSONArray(KEY_request_headers),
        requestBody = json.optString(KEY_request_body),

        // Response
        responseTimestamp = json.getLong(KEY_response_timestamp),
        responseStatusCode = json.getInt(KEY_response_status_code),
        responseHeaders = json.optJSONArray(KEY_response_headers),
        responseBody = json.optString(KEY_response_body),
        responseLength = json.optLong(KEY_response_length),
        gzippedLength = json.optLong(KEY_response_gzipped_length),

        // Exception
        exception = json.optJSONObject(KEY_exception)?.run { ExceptionModel(this) }
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_threadName, threadName)
            put(KEY_method, method)
            put(KEY_url, url)

            put(KEY_connect_timeout_ms, connectTimeoutMillis)
            put(KEY_read_timeout_ms, readTimeoutMillis)
            put(KEY_write_timeout_ms, writeTimeoutMillis)

            put(KEY_request_timestamp, requestTimestamp)
            put(KEY_request_headers, requestHeaders)
            put(KEY_request_body, requestBody)

            put(KEY_response_status_code, responseStatusCode)
            put(KEY_response_timestamp, responseTimestamp)
            put(KEY_response_body, responseBody)
            put(KEY_response_headers, responseHeaders)
            put(KEY_response_length, responseLength)

            putOpt(KEY_response_gzipped_length, gzippedLength)
            putOpt(KEY_exception, exception?.toJsonObject())
        }
    }

}