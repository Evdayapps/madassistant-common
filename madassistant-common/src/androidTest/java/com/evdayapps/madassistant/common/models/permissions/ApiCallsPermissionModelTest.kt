package com.evdayapps.madassistant.common.models.permissions

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiCallsPermissionModelTest {

    private fun testJson(
        enabled: Boolean = false,
        read: Boolean = false,
        share: Boolean = false,
        filterMethod: String? = null,
        filterUrl: String? = null
    ): ApiCallsPermissionModel {
        val input = ApiCallsPermissionModel(
            enabled = enabled,
            read = read,
            share = share,
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
            read = true,
            share = true,
            filterMethod = ".*(GET|POST).*",
            filterUrl = null
        )
    }

    @Test
    fun withRegexUrlFilter() {
        testJson(
            enabled = true,
            read = true,
            filterMethod = null,
            filterUrl = "/records/.*"
        ).apply {
            assertEquals(false, this.share)
        }
    }

}