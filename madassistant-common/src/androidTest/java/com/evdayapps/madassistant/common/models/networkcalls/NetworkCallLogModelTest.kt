package com.evdayapps.madassistant.common.models.networkcalls

import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkCallLogModelTest {

    @Test
    fun testOptionsToJson() {
        val options = Options(
            threadName = "Thread1",
            connectTimeoutMillis = 1000,
            readTimeoutMillis = 1000,
            writeTimeoutMillis = 1000,
            protocol = "HTTP/1.1"
        )
        val json = options.toJson()
        assertEquals("Thread1", json.getString("threadName"))
        assertEquals(1000, json.getInt("connectTimeoutMillis"))
    }

    @Test
    fun testOptionsFromJson() {
        val json = JSONObject()
            .put("threadName", "Thread1")
            .put("connectTimeoutMillis", 1000)
        val options = Options.fromJson(json)
        assertEquals("Thread1", options.threadName)
        assertEquals(1000, options.connectTimeoutMillis)
    }

    @Test
    fun testHandshakeToJson() {
        val handshake = Handshake(
            protocolVersion = "TLSv1.3",
            cipherSuite = "TLS_AES_128_GCM_SHA256"
        )
        val json = handshake.toJson()
        assertEquals("TLSv1.3", json.getString("protocolVersion"))
        assertEquals("TLS_AES_128_GCM_SHA256", json.getString("cipherSuite"))
    }

    @Test
    fun testHandshakeFromJson() {
        val json = JSONObject()
            .put("protocolVersion", "TLSv1.3")
            .put("cipherSuite", "TLS_AES_128_GCM_SHA256")
        val handshake = Handshake.fromJson(json)
        assertEquals("TLSv1.3", handshake.protocolVersion)
        assertEquals("TLS_AES_128_GCM_SHA256", handshake.cipherSuite)
    }

    @Test
    fun testRequestToJson() {
        val request = Request(
            method = "GET",
            url = "http://example.com",
            timestamp = 1630681200000,
            body = "body"
        )
        val json = request.toJson()
        assertEquals("GET", json.getString("method"))
        assertEquals("http://example.com", json.getString("url"))
    }

    @Test
    fun testRequestFromJson() {
        val json = JSONObject()
            .put("method", "GET")
            .put("url", "http://example.com")
        val request = Request.fromJson(json)
        assertEquals("GET", request.method)
        assertEquals("http://example.com", request.url)
    }

    @Test
    fun testResponseToJson() {
        val response = Response(
            statusCode = 200,
            timestamp = 1630681200000,
            length = 1000
        )
        val json = response.toJson()
        assertEquals(200, json.getInt("statusCode"))
        assertEquals(1000, json.getLong("length"))
    }

    @Test
    fun testResponseFromJson() {
        val json = JSONObject()
            .put("statusCode", 200)
            .put("length", 1000)
        val response = Response.fromJson(json)
        assertEquals(200, response.statusCode)
        assertEquals(1000, response.length)
    }

    @Test
    fun testNetworkCallLogModelToJson() {
        val options = Options(threadName = "Thread1")
        val request = Request(method = "GET", url = "http://example.com")
        val response = Response(statusCode = 200)
        val networkCallLogModel = NetworkCallLogModel(
            options = options,
            request = request,
            response = response
        )
        val json = networkCallLogModel.toJson()
        assertEquals("Thread1", json.getJSONObject("options").getString("threadName"))
        assertEquals("GET", json.getJSONObject("request").getString("method"))
        assertEquals(200, json.getJSONObject("response").getInt("statusCode"))
    }

    @Test
    fun testNetworkCallLogModelFromJson() {
        val json = JSONObject()
            .put("options", JSONObject().put("threadName", "Thread1"))
            .put("request", JSONObject().put("method", "GET").put("url", "http://example.com"))
            .put("response", JSONObject().put("statusCode", 200))
        val networkCallLogModel = NetworkCallLogModel.fromJson(json)
        assertEquals("Thread1", networkCallLogModel.options?.threadName)
        assertEquals("GET", networkCallLogModel.request?.method)
        assertEquals(200, networkCallLogModel.response?.statusCode)
    }
}
