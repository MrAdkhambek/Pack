package com.adkhambek.pack.text

import android.R
import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
internal class ContextUtilityTest {

    @Test
    fun `test plain text`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val plainText = "Text"
        val text = Text(plainText)

        Assertions.assertEquals(context.getString(text), plainText)
    }

    @Test
    fun `test res text`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val text = Text(R.string.autofill)

        Assertions.assertEquals(context.getString(text), context.getString(R.string.autofill))
    }

    @Test
    fun `test plural text with quantity one`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val resId = com.adkhambek.pack.text.test.R.plurals.items
        val text = Text.PluralText(resId, quantity = 1, formatArgs = listOf(1))

        Assertions.assertEquals("1 item", context.getString(text))
    }

    @Test
    fun `test plural text with quantity other`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val resId = com.adkhambek.pack.text.test.R.plurals.items
        val text = Text.PluralText(resId, quantity = 5, formatArgs = listOf(5))

        Assertions.assertEquals("5 items", context.getString(text))
    }

    @Test
    fun `test plural text without formatArgs`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val resId = com.adkhambek.pack.text.test.R.plurals.items
        val text = Text.PluralText(resId, quantity = 1)
        val expected = context.resources.getQuantityString(resId, 1)

        Assertions.assertEquals(expected, context.getString(text))
    }
}
