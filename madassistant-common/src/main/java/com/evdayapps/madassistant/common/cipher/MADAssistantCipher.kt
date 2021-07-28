package com.evdayapps.madassistant.common.cipher

interface MADAssistantCipher {

    fun decrypt(
        cipherText: String
    ): String?

    fun encrypt(
        plainText: String
    ): String
}