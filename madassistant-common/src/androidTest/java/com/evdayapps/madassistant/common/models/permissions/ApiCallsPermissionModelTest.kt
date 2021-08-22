package com.evdayapps.madassistant.common.models.permissions

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiCallsPermissionModelTest {

    private fun testJson(
        enabled: Boolean = false,
        filterMethod: String? = null,
        filterUrl: String? = null
    ): ApiCallsPermissionModel {
        val input = ApiCallsPermissionModel(
            enabled = enabled,
            filterMethod = filterMethod,
            filterUrl = filterUrl
        )

        val output = ApiCallsPermissionModel(input.toJsonObject())
        assertEquals(input, output)

        return output
    }

    @Test
    fun serializeSimpleObject() {
        testJson(
            enabled = true,
            filterMethod = ".*(GET|POST).*",
            filterUrl = null
        )
    }

    @Test
    fun withRegexUrlFilter() {
        testJson(
            enabled = true,
            filterMethod = null,
            filterUrl = "/records/.*"
        )
    }

}