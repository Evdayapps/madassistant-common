package com.evdayapps.madassistant.common.kotlinx

import org.json.JSONObject

fun JSONObject.getStringOrNull(key: String): String? {
    return when {
        this.has(key) -> getString(key)
        else -> null
    }
}

fun <T> JSONObject.getOr(key: String, defaultValue: T): T {
    return try {
        when {
            has(key) -> get(key) as T
            else -> defaultValue
        }
    } catch (ex: Exception) {
        defaultValue
    }
}