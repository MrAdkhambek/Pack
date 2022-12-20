package me.adkhambek.pack.text

import android.content.Context
import android.os.Parcelable
import androidx.annotation.CheckResult
import androidx.annotation.RestrictTo

/**
 * ```kotlin
 * val text: Text = ...
 * context.getString(text)
 * ```
 * Load a [String] resource.
 *
 * @param text the [Text]
 * @return the [String] data associated with the resource or plain string
 */
@CheckResult
public fun Context.getString(text: Text): String = when (text) {
    is Text.PlainText -> text.value
    is Text.ResText -> {
        val formatArgs = text.formatArgs?.toTypedArray()

        if (!formatArgs.isNullOrEmpty()) getString(text.resId, *formatArgs)
        else getString(text.resId)
    }
}