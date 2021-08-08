package com.evdayapps.madassistant.common.models.analytics

import junit.framework.Assert.assertEquals
import org.junit.Test

class AnalyticsEventModelTest {

    private fun runSerializationTest(
        threadName: String,
        destination: String,
        name: String,
        params: Map<String, Any?>
    ) {
        val input = AnalyticsEventModel(
            threadName = threadName,
            destination = destination,
            name = name,
            params = params
        )
        val output = AnalyticsEventModel(input.toJsonObject())
        assertEquals(input, output)
    }

    @Test
    fun serializeEventWithNoParams() {
        runSerializationTest(
            threadName = "Thread001",
            destination = "FakeDestination",
            name = "EventName",
            params = mapOf()
        )
    }

    @Test
    fun serializeEventWithParams() {
        runSerializationTest(
            threadName = "Thread001",
            destination = "FakeDestination",
            name = "EventName",
            params = mapOf(
                "eventName" to "Test",
                "type" to "screen_view"
            )
        )
    }

}