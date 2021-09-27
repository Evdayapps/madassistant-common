package com.evdayapps.madassistant.common.cipher

interface MADAssistantCipher {

    fun decrypt(
        cipherText: String,
        passphrase : String? = null
    ): String?

    fun encrypt(
        plainText: String
    ): String
}