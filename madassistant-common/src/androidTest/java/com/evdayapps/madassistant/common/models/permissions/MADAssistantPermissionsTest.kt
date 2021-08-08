package com.evdayapps.madassistant.common.models.permissions

import org.junit.Assert.assertEquals

class MADAssistantPermissionsTest {

    private fun runSerializationAssertation(
        timestampStart: Long? = null,
        timestampEnd: Long? = null,
        deviceId: String? = null,
        networkCalls: ApiCallsPermissionModel = ApiCallsPermissionModel(),
        analytics: AnalyticsPermissionModel = AnalyticsPermissionModel(),
        exceptions: ExceptionsPermissionModel = ExceptionsPermissionModel(),
        genericLogs: GenericLogsPermissionModel = GenericLogsPermissionModel(),
        randomString: String? = System.currentTimeMillis().toString()
    ): MADAssistantPermissions {
        val input = MADAssistantPermissions(

        )

        val output = MADAssistantPermissions(input.toJsonObject())
        
        assertEquals(input, output)
        
        return output
    }

}