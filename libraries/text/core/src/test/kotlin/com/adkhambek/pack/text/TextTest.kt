package com.adkhambek.pack.text

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class TextTest {

    @Test
    fun `PlainText creation and equality`() {
        val text1 = Text.PlainText("hello")
        val text2 = Text.PlainText("hello")
        val text3 = Text.PlainText("world")

        assertEquals(text1, text2)
        assertNotEquals(text1, text3)
        assertEquals("hello", text1.value)
    }

    @Test
    fun `ResText creation with default empty formatArgs`() {
        val text = Text.ResText(resId = 42)

        assertEquals(42, text.resId)
        assertEquals(emptyList<Any>(), text.formatArgs)
    }

    @Test
    fun `ResText creation with formatArgs`() {
        val args = listOf("arg1", 2)
        val text = Text.ResText(resId = 42, formatArgs = args)

        assertEquals(42, text.resId)
        assertEquals(args, text.formatArgs)
    }

    @Test
    fun `ResText equality`() {
        val text1 = Text.ResText(resId = 42, formatArgs = listOf("a"))
        val text2 = Text.ResText(resId = 42, formatArgs = listOf("a"))
        val text3 = Text.ResText(resId = 99)

        assertEquals(text1, text2)
        assertNotEquals(text1, text3)
    }

    @Test
    fun `PluralText creation with default empty formatArgs`() {
        val text = Text.PluralText(resId = 10, quantity = 5)

        assertEquals(10, text.resId)
        assertEquals(5, text.quantity)
        assertEquals(emptyList<Any>(), text.formatArgs)
    }

    @Test
    fun `PluralText creation with formatArgs`() {
        val args = listOf("5")
        val text = Text.PluralText(resId = 10, quantity = 5, formatArgs = args)

        assertEquals(10, text.resId)
        assertEquals(5, text.quantity)
        assertEquals(args, text.formatArgs)
    }

    @Test
    fun `PluralText equality`() {
        val text1 = Text.PluralText(resId = 10, quantity = 5, formatArgs = listOf("a"))
        val text2 = Text.PluralText(resId = 10, quantity = 5, formatArgs = listOf("a"))
        val text3 = Text.PluralText(resId = 10, quantity = 3)

        assertEquals(text1, text2)
        assertNotEquals(text1, text3)
    }

    @Test
    fun `companion invoke with String produces PlainText`() {
        val text: Text = Text("hello")

        assertTrue(text is Text.PlainText)
        assertEquals("hello", (text as Text.PlainText).value)
    }

    @Test
    fun `companion invoke with resId and no args produces ResText with empty formatArgs`() {
        val text: Text = Text(42)

        assertTrue(text is Text.ResText)
        val resText = text as Text.ResText
        assertEquals(42, resText.resId)
        assertEquals(emptyList<Any>(), resText.formatArgs)
    }

    @Test
    fun `companion invoke with resId and args produces ResText with formatArgs`() {
        val text: Text = Text(42, "arg1", "arg2")

        assertTrue(text is Text.ResText)
        val resText = text as Text.ResText
        assertEquals(42, resText.resId)
        assertEquals(listOf("arg1", "arg2"), resText.formatArgs)
    }

    @Test
    fun `all subtypes are instances of Text`() {
        val plain: Text = Text.PlainText("x")
        val res: Text = Text.ResText(1)
        val plural: Text = Text.PluralText(1, 1)

        assertTrue(plain is Text)
        assertTrue(res is Text)
        assertTrue(plural is Text)
    }
}
