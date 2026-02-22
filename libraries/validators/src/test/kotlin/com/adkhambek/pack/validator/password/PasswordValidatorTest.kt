package com.adkhambek.pack.validator.password

import com.adkhambek.pack.validator.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

internal class PasswordValidatorTest {

    private val validator: Validator<String?> = PasswordValidator()

    @Test
    fun `valid password with uppercase lowercase and digit`() {
        assertTrue(validator("Aa12345678").isEmpty())
    }

    @Test
    fun `valid password at minimum length`() {
        assertTrue(validator("Aa1bbb").isEmpty())
    }

    @Test
    fun `valid password with mixed characters`() {
        assertTrue(validator("Password1").isEmpty())
    }

    @Test
    fun `invalid when missing lowercase`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("A12345678"))
    }

    @Test
    fun `invalid when missing uppercase`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("a12345678"))
    }

    @Test
    fun `invalid when missing digit`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("Aabcdefgh"))
    }

    @Test
    fun `invalid when too short`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("Aa1bb"))
    }

    @Test
    fun `invalid when null`() {
        assertEquals(setOf(PasswordValidator.KEY), validator(null))
    }

    @Test
    fun `invalid when empty`() {
        assertEquals(setOf(PasswordValidator.KEY), validator(""))
    }

    @Test
    fun `invalid when blank`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("   "))
    }

    @Test
    fun `invalid with special characters`() {
        assertEquals(setOf(PasswordValidator.KEY), validator("Aa1@bcde"))
    }

    @Test
    fun `valid with custom pattern`() {
        val custom = PasswordValidator(Pattern.compile("^.{4,}$"))
        assertTrue(custom("abcd").isEmpty())
    }

    @Test
    fun `invalid with custom pattern`() {
        val custom = PasswordValidator(Pattern.compile("^.{4,}$"))
        assertEquals(setOf(PasswordValidator.KEY), custom("abc"))
    }
}
