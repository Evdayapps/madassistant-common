package com.evdayapps.madassistant.common.cipher

/**
 * Interface for encrypting and decrypting text
 */
interface MADAssistantCipher {

    /**
     * Decrypt [cipherText]
     * [passphrase] is an
     */
    fun decrypt(
        cipherText: String,
        passphrase : String? = null
    ): String?

    fun encrypt(
        plainText: String,
        passphrase : String? = null
    ): String
}