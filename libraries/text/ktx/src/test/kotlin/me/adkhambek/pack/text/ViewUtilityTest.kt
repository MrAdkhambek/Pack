package me.adkhambek.pack.text

import android.R
import android.content.Context
import android.os.Build
import android.view.View
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
internal class ViewUtilityTest {

    @Test
    fun `test plain text`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val view = View(context)
        val plainText = "Text"

        val text = Text(plainText)
        Assertions.assertEquals(view.getString(text), plainText)
    }

    @Test
    fun `test res text`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val view = View(context)

        val text = Text(R.string.autofill)
        Assertions.assertEquals(view.getString(text), context.getString(R.string.autofill))
    }
}