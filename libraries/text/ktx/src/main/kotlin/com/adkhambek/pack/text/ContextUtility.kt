package com.adkhambek.pack.text

import android.content.Context
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment

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
        if (text.formatArgs.isNotEmpty()) getString(text.resId, *text.formatArgs.toTypedArray())
        else getString(text.resId)
    }

    is Text.PluralText -> {
        if (text.formatArgs.isNotEmpty()) {
            resources.getQuantityString(text.resId, text.quantity, *text.formatArgs.toTypedArray())
        } else {
            resources.getQuantityString(text.resId, text.quantity)
        }
    }
}

/**
 * ```kotlin
 * val text: Text = ...
 * fragment.getString(text)
 * ```
 * Load a [String] resource.
 *
 * @param text the [Text]
 * @return the [String] data associated with the resource or plain string
 */
@CheckResult
public fun Fragment.getString(text: Text): String = requireContext().getString(text)
