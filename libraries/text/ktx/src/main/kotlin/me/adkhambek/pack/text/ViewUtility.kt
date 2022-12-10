package me.adkhambek.pack.text

import android.view.View

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
public fun View.getString(text: Text): String = context.getString(text)