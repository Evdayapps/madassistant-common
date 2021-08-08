package com.evdayapps.madassistant.common.models.exceptions

import junit.framework.Assert.assertEquals
import org.junit.Test
import java.io.IOException
import java.security.InvalidKeyException

class ExceptionModelTest {

    private fun runAssertations(input: ExceptionModel, output: ExceptionModel) {
        assertEquals(input.exceptionThreadName, output.exceptionThreadName)
        assertEquals(input.type, output.type)
        assertEquals(input.message, output.message)
        assertEquals(input.cause, output.cause)
        assertEquals(input.crash, output.crash)
        assertEquals(input.stackTrace, output.stackTrace)

        assertEquals(input.threads?.size, output.threads?.size)
        input.threads?.keys?.forEach { key ->
            val inThread = input.threads?.get(key)
            val outThread = output.threads?.get(key)
            assertEquals(inThread?.size, outThread?.size)
            for (i in 0 until (inThread?.size ?: 0)) {
                assertEquals(inThread?.get(i), outThread?.get(i))
            }
        }
    }

    @Test
    fun serializeExceptionWithoutCause() {
        val input = ExceptionModel(
            threadName = "ThreadName--1",
            isCrash = true,
            throwable = IOException(),
            nested = true
        )
        val output = ExceptionModel(input.toJsonObject())
        runAssertations(input = input, output = output)
    }

    @Test
    fun serializeNestedExceptionWithCause() {
        val input = ExceptionModel(
            threadName = "ThreadName--1",
            isCrash = true,
            throwable = IOException(InvalidKeyException())
        )
        val output = ExceptionModel(input.toJsonObject())
        runAssertations(input = input, output = output)

    }

}