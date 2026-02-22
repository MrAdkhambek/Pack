package com.adkhambek.pack.validator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class ValidatorTest {

    private object KeyA : Validator.Key
    private object KeyB : Validator.Key

    private val alwaysValid = Validator<String> { emptySet() }
    private val alwaysInvalidA = Validator<String> { setOf(KeyA) }
    private val alwaysInvalidB = Validator<String> { setOf(KeyB) }

    @Test
    fun `and combines two valid validators`() {
        val combined = alwaysValid and alwaysValid
        assertTrue(combined("test").isEmpty())
    }

    @Test
    fun `and returns first error when first fails`() {
        val combined = alwaysInvalidA and alwaysValid
        assertEquals(setOf(KeyA), combined("test"))
    }

    @Test
    fun `and returns second error when second fails`() {
        val combined = alwaysValid and alwaysInvalidB
        assertEquals(setOf(KeyB), combined("test"))
    }

    @Test
    fun `and returns both errors when both fail`() {
        val combined = alwaysInvalidA and alwaysInvalidB
        assertEquals(setOf(KeyA, KeyB), combined("test"))
    }

    @Test
    fun `and chains three validators`() {
        val keyC = object : Validator.Key {}
        val alwaysInvalidC = Validator<String> { setOf(keyC) }

        val combined = alwaysInvalidA and alwaysInvalidB and alwaysInvalidC
        assertEquals(setOf(KeyA, KeyB, keyC), combined("test"))
    }

    @Test
    fun `functional interface creates validator from lambda`() {
        val lengthValidator = Validator<String> { value ->
            if (value.length >= 3) emptySet() else setOf(KeyA)
        }
        assertTrue(lengthValidator("abc").isEmpty())
        assertEquals(setOf(KeyA), lengthValidator("ab"))
    }
}
