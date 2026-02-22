package com.adkhambek.pack.validator.name

import com.adkhambek.pack.validator.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.util.regex.Pattern

internal class UserNameValidatorTest {

    private val validator: Validator<String?> = UserNameValidator()

    @Test
    fun `valid username with letters only`() {
        assertTrue(validator("john").isEmpty())
    }

    @Test
    fun `valid username with digits only`() {
        assertTrue(validator("123").isEmpty())
    }

    @Test
    fun `valid username with letters and digits`() {
        assertTrue(validator("john123").isEmpty())
    }

    @Test
    fun `valid username at minimum length`() {
        assertTrue(validator("abc").isEmpty())
    }

    @Test
    fun `valid username with uppercase`() {
        assertTrue(validator("John").isEmpty())
    }

    @Test
    fun `valid long username`() {
        assertTrue(validator("abcdefghijklmnopqrstuvwxyz").isEmpty())
    }

    @Test
    fun `invalid when too short with one char`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("a"))
    }

    @Test
    fun `invalid when too short with two chars`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("ab"))
    }

    @Test
    fun `invalid when null`() {
        assertEquals(setOf(UserNameValidator.KEY), validator(null))
    }

    @Test
    fun `invalid when empty`() {
        assertEquals(setOf(UserNameValidator.KEY), validator(""))
    }

    @Test
    fun `invalid when blank`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("   "))
    }

    @Test
    fun `invalid with special characters`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("user@name"))
    }

    @Test
    fun `invalid with underscore`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("user_name"))
    }

    @Test
    fun `invalid with hyphen`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("user-name"))
    }

    @Test
    fun `invalid with space`() {
        assertEquals(setOf(UserNameValidator.KEY), validator("user name"))
    }

    @Test
    fun `valid with custom pattern`() {
        val custom = UserNameValidator(Pattern.compile("^[a-z_]{2,}$"))
        assertTrue(custom("a_b").isEmpty())
    }

    @Test
    fun `invalid with custom pattern`() {
        val custom = UserNameValidator(Pattern.compile("^[a-z_]{2,}$"))
        assertEquals(setOf(UserNameValidator.KEY), custom("a"))
    }
}
