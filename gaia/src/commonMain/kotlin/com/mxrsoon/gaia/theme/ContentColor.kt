package com.mxrsoon.gaia.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * CompositionLocal containing the preferred content color for a given position in the hierarchy.
 * This typically represents the `on` color for a color in [ColorScheme]. For example, if the
 * background color is [ColorScheme.surface], this color is typically set to
 * [ColorScheme.onSurface].
 *
 * This color should be used for any typography/iconography, to ensure that the color of these
 * adjusts when the background color changes. For example, on a dark background, text should be
 * light, and on a light background, text should be dark.
 */
val LocalContentColor = compositionLocalOf { Color.Black }