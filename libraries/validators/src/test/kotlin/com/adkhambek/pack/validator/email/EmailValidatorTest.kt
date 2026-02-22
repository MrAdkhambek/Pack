package com.adkhambek.pack.validator.email

import com.adkhambek.pack.validator.Validator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class EmailValidatorTest {

    private val validator: Validator<String?> = EmailValidator()

    @Test
    fun `valid simple email`() {
        assertTrue(validator("user@example.com").isEmpty())
    }

    @Test
    fun `valid email with dots in local part`() {
        assertTrue(validator("first.last@example.com").isEmpty())
    }

    @Test
    fun `valid email with plus in local part`() {
        assertTrue(validator("user+tag@example.com").isEmpty())
    }

    @Test
    fun `valid email with underscore in local part`() {
        assertTrue(validator("user_name@example.com").isEmpty())
    }

    @Test
    fun `valid email with hyphen in local part`() {
        assertTrue(validator("user-name@example.com").isEmpty())
    }

    @Test
    fun `invalid email with percent in local part`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user%name@example.com"))
    }

    @Test
    fun `valid email with digits in local part`() {
        assertTrue(validator("user123@example.com").isEmpty())
    }

    @Test
    fun `valid email with two letter tld`() {
        assertTrue(validator("user@example.co").isEmpty())
    }

    @Test
    fun `valid email with hyphen in domain`() {
        assertTrue(validator("user@my-domain.com").isEmpty())
    }

    @Test
    fun `valid email with short domain`() {
        assertTrue(validator("a@b.co").isEmpty())
    }

    @Test
    fun `invalid when null`() {
        assertEquals(setOf(EmailValidator.KEY), validator(null))
    }

    @Test
    fun `invalid when empty`() {
        assertEquals(setOf(EmailValidator.KEY), validator(""))
    }

    @Test
    fun `invalid when blank`() {
        assertEquals(setOf(EmailValidator.KEY), validator("   "))
    }

    @Test
    fun `invalid without at sign`() {
        assertEquals(setOf(EmailValidator.KEY), validator("userexample.com"))
    }

    @Test
    fun `invalid without domain`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user@"))
    }

    @Test
    fun `invalid without local part`() {
        assertEquals(setOf(EmailValidator.KEY), validator("@example.com"))
    }

    @Test
    fun `invalid without tld`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user@example"))
    }

    @Test
    fun `invalid with double at sign`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user@@example.com"))
    }

    @Test
    fun `invalid with space`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user @example.com"))
    }

    @Test
    fun `invalid domain starting with hyphen`() {
        assertEquals(setOf(EmailValidator.KEY), validator("user@-example.com"))
    }
}
