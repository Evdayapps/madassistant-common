package com.evdayapps.madassistant.common.models.exceptions

import com.evdayapps.madassistant.common.kotlinx.getStringOrNull
import org.json.JSONArray
import org.json.JSONObject

/**
 * @property exceptionThreadName The thread on which the exception occured
 * @property crash Is this a crash?
 * @property type The className of the exception
 * @property message A custom message to explain the error
 * @property throwableMessage The message retrieved from the throwable
 * @property stackTrace Stacktrace from the throwable
 * @property cause Cause of the exception, if any
 * @property threads Stacktraces for the other threads
 */
data class ExceptionModel(
    var exceptionThreadName: String,
    var crash: Boolean,
    var type: String?,
    var message: String? = null,
    var throwableMessage: String?,
    var data: JSONObject? = null,
    var stackTrace: List<ExceptionStacktraceLineModel>,
    var cause: ExceptionModel?,
    var threads: Map<String, List<ExceptionStacktraceLineModel>>?
) {

    companion object {
        private const val KEY_exceptionThreadName = "exceptionThreadName"
        private const val KEY_isCrash = "isCrash"
        private const val KEY_type = "type"
        private const val KEY_message = "message"
        private const val KEY_data = "data"
        private const val KEY_throwableMessage = "throwableMessage"
        private const val KEY_stacktrace = "stacktrace"
        private const val KEY_cause = "cause"
        private const val KEY_threads = "threads"

        private fun getThreadsStacktrace(json: JSONObject): Map<String, List<ExceptionStacktraceLineModel>> {
            try {
                val map = mutableMapOf<String, List<ExceptionStacktraceLineModel>>()
                json.keys().forEach { key ->
                    val arr = json.getJSONArray(key)
                    val list = mutableListOf<ExceptionStacktraceLineModel>()
                    for (i in 0 until arr.length()) {
                        val line = arr.getJSONObject(i)
                            .run { ExceptionStacktraceLineModel(this) }
                        list.add(line)
                    }
                    map[key] = list
                }

                return map
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            return mapOf()
        }
    }

    /**
     * @param throwable The throwable to log
     * @param isCrash Whether this is a crash or a handled exception
     * @param nested Is this a nested model ([cause] for another exception)?
     *               if yes, wont log threads
     */
    constructor(
        threadName: String,
        message: String? = null,
        data: JSONObject? = null,
        throwable: Throwable,
        isCrash: Boolean,
        nested: Boolean = false,
    ) : this(
        exceptionThreadName = threadName,
        crash = isCrash,
        type = throwable.javaClass.canonicalName,
        throwableMessage = throwable.message,
        message = message,
        data = data,
        stackTrace = throwable.stackTrace.map { ExceptionStacktraceLineModel(it) },
        cause = throwable.cause?.run {
            ExceptionModel(
                threadName = threadName,
                throwable = this,
                isCrash = false,
                nested = true
            )
        },
        threads = when {
            !nested -> mutableMapOf<String, List<ExceptionStacktraceLineModel>>().apply {
                Thread.getAllStackTraces()
                    .map {
                        Pair(
                            first = it.key.name,
                            second = it.value.map { ExceptionStacktraceLineModel(it) }
                        )
                    }.apply {
                        putAll(this)
                    }
            }
            else -> null
        },
    )

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        exceptionThreadName = json.getString(KEY_exceptionThreadName),
        crash = json.optBoolean(KEY_isCrash, true),
        type = json.getStringOrNull(KEY_type),
        throwableMessage = json.getStringOrNull(KEY_throwableMessage),
        message = json.getStringOrNull(KEY_message),
        data = json.optJSONObject(KEY_data),
        cause = json.optJSONObject(KEY_cause)?.run {
            try {
                ExceptionModel(this)
            } catch (ex: Exception) {
                null
            }
        },
        threads = json.optJSONObject(KEY_threads)?.run { getThreadsStacktrace(this) },
        stackTrace = json.getJSONArray(KEY_stacktrace).run {
            mutableListOf<ExceptionStacktraceLineModel>().apply {
                for (i in 0 until this@run.length()) {
                    add(ExceptionStacktraceLineModel(getJSONObject(i)))
                }
            }
        }
    )

    @Throws(Exception::class)
    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(KEY_exceptionThreadName, exceptionThreadName)
            put(KEY_isCrash, crash)
            put(KEY_type, type)
            putOpt(KEY_message, message)
            putOpt(KEY_data, data)
            putOpt(KEY_throwableMessage, throwableMessage)
            cause?.run {
                put(KEY_cause, this.toJsonObject())
            }
            threads
                ?.apply {
                    val obj = JSONObject()
                    this.forEach {
                        val array = JSONArray()
                        it.value.forEach { value ->
                            array.put(value.toJsonObject())
                        }
                        obj.put(it.key, array)
                    }
                    put(KEY_threads, obj)
                }

            put(KEY_stacktrace, JSONArray().apply {
                stackTrace.forEach {
                    put(it.toJsonObject())
                }
            })
        }
    }
}