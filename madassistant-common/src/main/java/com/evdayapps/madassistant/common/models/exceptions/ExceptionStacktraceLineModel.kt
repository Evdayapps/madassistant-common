package com.evdayapps.madassistant.common.models.exceptions

import org.json.JSONObject

data class ExceptionStacktraceLineModel(
    val className: String,
    val fileName: String?,
    val nativeMethod: Boolean,
    val methodName: String,
    val lineNumber: Int
) {

    companion object {
        const val keyClassName = "className"
        const val keyFileName = "fileName"
        const val keyNativeMethod = "nativeMethod"
        const val keyMethodName = "methodName"
        const val keyLineNumber = "lineNumber"
    }

    val printLine by lazy {
        "$className.$methodName  ($fileName:$lineNumber)"
    }

    constructor(element: StackTraceElement) : this(
        className = element.className,
        fileName = element.fileName,
        nativeMethod = element.isNativeMethod,
        methodName = element.methodName,
        lineNumber = element.lineNumber
    )

    @Throws(Exception::class)
    constructor(json: JSONObject) : this(
        className = json.getString(keyClassName),
        fileName = json.optString(keyFileName),
        nativeMethod = json.getBoolean(keyNativeMethod),
        methodName = json.getString(keyMethodName),
        lineNumber = json.getInt(keyLineNumber),
    )

    fun toJsonObject(): JSONObject {
        return JSONObject().apply {
            put(keyClassName, className)
            put(keyFileName, fileName)
            put(keyNativeMethod, nativeMethod)
            put(keyMethodName, methodName)
            put(keyLineNumber, lineNumber)
        }
    }

}