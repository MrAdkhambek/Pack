package me.adkhambek.pack.text

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
}