package com.evdayapps.madassistant.common.models.networkcalls


import com.evdayapps.madassistant.common.models.exceptions.ExceptionModel
import org.json.JSONArray
import org.json.JSONObject

// Model representing a network call, including options, request, response, and exception details
data class NetworkCallLogModel(
    var options: Options? = null, // Connection options
    var request: Request? = null, // Request details
    var response: Response? = null, // Response details
    var exception: ExceptionModel? = null // Exception details (if any)
) {
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("options", options?.toJson())
            put("request", request?.toJson())
            put("response", response?.toJson())
            put("exception", exception?.toJsonObject()) // Assuming ExceptionModel has toJson
        }
    }

    companion object {
        fun fromJson(json: JSONObject): NetworkCallLogModel {
            return NetworkCallLogModel(
                options = json.optJSONObject("options")?.let { Options.fromJson(it) },
                request = json.optJSONObject("request")?.let { Request.fromJson(it) },
                response = json.optJSONObject("response")?.let { Response.fromJson(it) },
                exception = json.optJSONObject("exception")
                    ?.let { ExceptionModel(it) } // Assuming ExceptionModel has fromJson
            )
        }
    }
}

// Options for the network call, including timeouts and handshake details
data class Options(
    var threadName: String? = null, // Name of the thread handling the call
    var connectTimeoutMillis: Int? = null, // Connection timeout in milliseconds
    var readTimeoutMillis: Int? = null, // Read timeout in milliseconds
    var writeTimeoutMillis: Int? = null, // Write timeout in milliseconds
    var protocol: String? = null, // Protocol used (e.g., HTTP/1.1)
    var handshake: Handshake? = null // SSL/TLS handshake details
) {
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("threadName", threadName)
            put("connectTimeoutMillis", connectTimeoutMillis)
            put("readTimeoutMillis", readTimeoutMillis)
            put("writeTimeoutMillis", writeTimeoutMillis)
            put("protocol", protocol)
            put("handshake", handshake?.toJson())
        }
    }

    companion object {
        fun fromJson(json: JSONObject): Options {
            return Options(
                threadName = json.optString("threadName"),
                connectTimeoutMillis = json.optInt("connectTimeoutMillis"),
                readTimeoutMillis = json.optInt("readTimeoutMillis"),
                writeTimeoutMillis = json.optInt("writeTimeoutMillis"),
                protocol = json.optString("protocol"),
                handshake = json.optJSONObject("handshake")?.let { Handshake.fromJson(it) }
            )
        }
    }
}

// Details of the SSL/TLS handshake
data class Handshake(
    var protocolVersion: String? = null, // SSL/TLS protocol version
    var cipherSuite: String? = null // Cipher suite used for encryption
) {
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("protocolVersion", protocolVersion)
            put("cipherSuite", cipherSuite)
        }
    }

    companion object {
        fun fromJson(json: JSONObject): Handshake {
            return Handshake(
                protocolVersion = json.optString("protocolVersion"),
                cipherSuite = json.optString("cipherSuite")
            )
        }
    }
}

// Details of the HTTP request
data class Request(
    var method: String = "", // HTTP method (e.g., GET, POST)
    var url: String = "", // URL of the request
    var headers: JSONArray? = null, // HTTP headers
    var timestamp: Long = 0, // Timestamp of the request
    var contentType: String? = null,
    var body: String? = null // Request body
) {
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("method", method)
            put("url", url)
            put("headers", headers)
            put("timestamp", timestamp)
            put("contentType", contentType)
            put("body", body)
        }
    }

    companion object {
        fun fromJson(json: JSONObject): Request {
            return Request(
                method = json.optString("method"),
                url = json.optString("url"),
                headers = json.optJSONArray("headers"),
                timestamp = json.optLong("timestamp"),
                contentType = json.optString("contentType"),
                body = json.optString("body")
            )
        }
    }
}

// Details of the HTTP response
data class Response(
    var headers: JSONArray? = null, // HTTP headers
    var statusCode: Int? = null, // HTTP status code
    var timestamp: Long = 0, // Timestamp of the response
    var gzippedLength: Long? = null, // Length of gzipped response
    var length: Long? = null, // Length of response
    var contentType: String? = null,
    var body: String? = null // Response body
) {
    fun toJson(): JSONObject {
        return JSONObject().apply {
            put("headers", headers)
            put("statusCode", statusCode)
            put("timestamp", timestamp)
            put("gzippedLength", gzippedLength)
            put("length", length)
            put("contentType", contentType)
            put("body", body)
        }
    }

    companion object {
        fun fromJson(json: JSONObject): Response {
            return Response(
                headers = json.optJSONArray("headers"),
                statusCode = json.optInt("statusCode"),
                timestamp = json.optLong("timestamp"),
                gzippedLength = json.optLong("gzippedLength"),
                length = json.optLong("length"),
                contentType = json.optString("contentType"),
                body = json.optString("body")
            )
        }
    }
}
