package com.adkhambek.pack.validator.password

import com.adkhambek.pack.validator.Validator
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PasswordValidatorTest {

    private val validator: Validator<String?> = PasswordValidator()

    @Test
    fun `test correct password`() {
        Assertions.assertTrue(validator.isValid("Aa12345678").isEmpty())
    }

    @Test
    fun `test incorrect password`() {
        Assertions.assertTrue(validator.isValid("A12345678").contains(PasswordValidator.KEY))
    }
}
