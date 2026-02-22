package com.adkhambek.pack.text

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

/**
 * Text utility for using string resources avoid context
 */
public sealed interface Text {

    /**
     * ```kotlin
     *  val text: Text = Text.PlainText("Text here")
     *  // or short version
     *  val text: Text = Text("Text here")
     * ```
     */
    public data class PlainText(
        val value: String,
    ) : Text

    /**
     * ```kotlin
     *  val text: Text = Text.ResText(R.string.app_name)
     *  // or short version
     *  val text: Text = Text(R.string.app_name)
     *
     *  // <string name="hi">Hi %s</string>
     *  val text: Text = Text.ResText(R.string.hi, "Adam")
     *  // or short version
     *  val text: Text = Text(R.string.hi, "Adam")
     * ```
     */
    public data class ResText(
        @param:StringRes val resId: Int,
        val formatArgs: List<Any> = emptyList()
    ) : Text

    /**
     * ```kotlin
     *  val text: Text = Text.PluralText(R.plurals.items, quantity = 5)
     *  val text: Text = Text.PluralText(R.plurals.items, quantity = 5, listOf("5"))
     * ```
     */
    public data class PluralText(
        @param:PluralsRes val resId: Int,
        val quantity: Int,
        val formatArgs: List<Any> = emptyList()
    ) : Text

    public companion object {

        @JvmStatic
        public operator fun invoke(
            value: String,
        ): Text = PlainText(value)

        @JvmStatic
        public operator fun invoke(
            @StringRes resId: Int,
            vararg formatArgs: Any,
        ): Text = ResText(resId, formatArgs.asList())

        @JvmStatic
        public operator fun invoke(
            @PluralsRes resId: Int,
            quantity: Int,
            vararg formatArgs: Any,
        ): Text = PluralText(resId, quantity, formatArgs.asList())
    }
}
