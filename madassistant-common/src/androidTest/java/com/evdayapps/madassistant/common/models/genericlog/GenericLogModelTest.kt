package com.evdayapps.madassistant.common.models.genericlog

import android.util.Log
import junit.framework.Assert.assertEquals
import org.json.JSONObject
import org.junit.Test

class GenericLogModelTest {

    private fun testSerializing(
        threadName: String,
        type: Int,
        tag: String,
        message: String,
        data: JSONObject? = null
    ): GenericLogModel {
        val input = GenericLogModel(
            threadName = threadName,
            type = type,
            tag = tag,
            message = message,
            data = data
        )
        val output = GenericLogModel(input.toJsonObject())
        assertEquals(input, output)

        return output
    }

    @Test
    fun serializeWithoutData() {
        listOf(Log.DEBUG, Log.ERROR, Log.WARN, Log.INFO, Log.VERBOSE).forEach { type ->
            testSerializing(
                threadName = "Thread001",
                type = type,
                tag = "Test tag",
                message = "message"
            )
        }
    }

}