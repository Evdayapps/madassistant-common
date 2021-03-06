package com.evdayapps.madassistant.common.models.permissions

import org.junit.Assert.assertEquals
import org.junit.Test

class AnalyticsPermissionModelTest {

    private fun testJson(
        enabled: Boolean = false,
        filterDestination: String? = null,
        filterEventName: String? = null,
        filterParamData: String? = null
    ) {
        val input = AnalyticsPermissionModel(
            enabled = enabled,
            filterDestination = filterDestination,
            filterEventName = filterEventName,
            filterParamData = filterParamData
        )

        val output = AnalyticsPermissionModel(input.toJsonObject())
        assertEquals(input, output)
    }

    @Test
    fun serializeSimpleObject() {
        testJson(
            enabled = true,
            filterDestination = "Test*",
            filterEventName = null
        )
    }

    @Test
    fun serializeRegexFilter() {
        testJson(
            enabled = true,
            filterDestination = "(Destination1|Destination2).*",
            filterParamData = "\"screen_view\""
        )
    }
}