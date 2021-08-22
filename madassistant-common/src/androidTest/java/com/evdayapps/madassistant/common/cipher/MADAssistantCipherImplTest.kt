package com.evdayapps.madassistant.common.cipher

import com.evdayapps.madassistant.common.models.permissions.MADAssistantPermissions
import org.junit.Assert.*
import org.junit.Test

class MADAssistantCipherImplTest {

    @Test
    fun encipherAndDecipherAString() {
        val passphrase = "password"
        val cipher = MADAssistantCipherImpl(passPhrase = passphrase)

        val plainText = "Lorem Ipsum is simply dummy text of the printing and typesetting industry." +
            " Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an" +
            " unknown printer took a galley of type and scrambled it to make a type specimen book." +
            " It has survived not only five centuries, but also the leap into electronic" +
            " typesetting, remaining essentially unchanged. It was popularised in the 1960s with " +
            "the release of Letraset sheets containing Lorem Ipsum passages, and more recently " +
            "with desktop publishing software like Aldus PageMaker including versions of " +
            "Lorem Ipsum."

        val ciphered =  cipher.encrypt(plainText)

        val deciphered = cipher.decrypt(ciphered)

        assertEquals(plainText, deciphered)
    }

}