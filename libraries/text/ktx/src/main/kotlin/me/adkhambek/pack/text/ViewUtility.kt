@file:Suppress("NOTHING_TO_INLINE")

package me.adkhambek.pack.text

import android.view.View
import android.widget.TextView
import androidx.annotation.CheckResult

/**
 * ```kotlin
 * val text: Text = ...
 * view.getString(text)
 * ```
 * Load a [String] resource.
 *
 * @param text the [Text]
 * @return the [String] data associated with the resource or plain string
 */
@CheckResult
public inline fun View.getString(text: Text): String = context.getString(text)

/**
 * ```kotlin
 * val text: Text = ...
 * textView.setText(text)
 * ```
 * @param text the [Text]
 */
public inline fun TextView.setText(text: Text) {
    this.text = context.getString(text)
}